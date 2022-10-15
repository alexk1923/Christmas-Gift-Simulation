package strategy;

import java.util.ArrayList;

public final class BabyAverageScore implements AverageScoreStrategy {

    @Override
    public Double calcAverageScore(final ArrayList<Double> niceScoreList) {
        final double perfectScore = 10.0;
        return perfectScore;
    }
}
