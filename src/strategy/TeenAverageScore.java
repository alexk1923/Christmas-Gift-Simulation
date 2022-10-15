package strategy;

import java.util.ArrayList;

public final class TeenAverageScore implements AverageScoreStrategy {
    @Override
    public Double calcAverageScore(final ArrayList<Double> niceScoreList) {
        double sum = 0.0;
        for (int i = 0; i < niceScoreList.size(); i++) {
            sum += niceScoreList.get(i) * (i + 1);
        }
        int n = niceScoreList.size();
        int divider = n * (n + 1) / 2;
        return sum / divider;
    }
}
