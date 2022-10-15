package strategy;

import java.util.ArrayList;

public final class KidAverageScore implements AverageScoreStrategy {
    @Override
    public Double calcAverageScore(final ArrayList<Double> niceScoreHistory) {
        Double sum = 0.0;
        for (Double d : niceScoreHistory) {
            sum += d;
        }
        return sum / niceScoreHistory.size();
    }
}
