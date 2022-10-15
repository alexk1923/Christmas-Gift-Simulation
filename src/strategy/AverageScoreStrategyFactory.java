package strategy;

import static common.Constants.ADULT_AGE;
import static common.Constants.TEEN_AGE;
import static common.Constants.KID_AGE;

public class AverageScoreStrategyFactory {

    private AverageScoreStrategyFactory() {

    }

    public static AverageScoreStrategy createAverageScoreStrategy(final int age) {
        if (age < KID_AGE) {
            return new BabyAverageScore();
        } else if (age < TEEN_AGE) {
            return new KidAverageScore();
        } else if (age <= ADULT_AGE) {
            return new TeenAverageScore();
        }
        return null;
    }
}

