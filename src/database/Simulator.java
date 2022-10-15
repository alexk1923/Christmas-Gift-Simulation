package database;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import entities.Santa;
import entities.Child;
import fileio.ChildOutputList;
import fileio.DataList;
import fileio.OutputData;
import strategy.AverageScoreStrategyFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static common.Constants.ADULT_AGE;

public final class Simulator {
    private final Database db;

    public Simulator(final Database db) {
        this.db = db;
    }

    public void execute(final int testNo) throws IOException {
        int n = db.getNumberOfYears();
        OutputData outputData = new OutputData();

        // get the output for each year
        for (int i = 0; i <= n; i++) {
            outputData.getAnnualChildren().add(round(i));
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(Constants.OUTPUT_PATH + testNo + ".json"),
                outputData);
    }

    public static double calcTotalAverageScore(final ArrayList<Child> children) {
        double sum = 0.0;
        for (Child c : children) {
            sum += c.getAverageNiceScore();
        }
        return sum;
    }

    public static void updateAverageScores(final ArrayList<Child> children) {
        for (Child child : children) {
            // Apply a strategy for each child and set its average score field
            double childAverageScore = AverageScoreStrategyFactory
                    .createAverageScoreStrategy(child.getAge())
                    .calcAverageScore(child.getNiceScoreHistory());
            final int percentage = 100;
            final int perfectScore = 10;
            childAverageScore += childAverageScore * child.getNiceScoreBonus() / percentage;
            child.setAverageNiceScore(childAverageScore < perfectScore
                    ? childAverageScore : perfectScore);
        }
    }


    public ChildOutputList firstRound() {
        DataList dataList = db.getInitialData();

        // remove all young adults
        dataList.getChildren().removeIf(child -> child.getAge() > ADULT_AGE);

        // add the current nice score into the history
        for (Child c: dataList.getChildren()) {
            c.getNiceScoreHistory().add(c.getNiceScore());
        }

        // update average score for every child
        updateAverageScores(dataList.getChildren());

        double totalAverageScore = calcTotalAverageScore(dataList.getChildren());
        double budgetUnit = db.getSantaBudget() / totalAverageScore;
        Santa s = Santa.getInstance();
        s.setData(db.getSantaBudget(), budgetUnit, dataList.getSantaGiftsList(),
                dataList.getChildren());

        return s.giveGifts(dataList.getChildren());
    }

    public ChildOutputList round(final int i) {

        if (i == 0) {
            return firstRound();
        }

        // update using annual changes from the database
        Santa santa = Santa.getInstance();
        santa.annualUpdate(db.getAnnualChanges().get(i - 1));

        // distribute gifts to the children
        return santa.giveGifts(santa.getChildren());
    }
}
