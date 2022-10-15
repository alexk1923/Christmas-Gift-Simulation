package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Database;
import database.Simulator;

import java.io.File;
import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        final int noTests = 30;
        for (int i = 1; i <= noTests; i++) {
            String fileName = "test" + i + ".json";
            Database database = objectMapper.readValue(new File("./tests/" + fileName),
                    Database.class);
            Simulator simulator = new Simulator(database);
            simulator.execute(i);

        }
        Checker.calculateScore();
    }
}
