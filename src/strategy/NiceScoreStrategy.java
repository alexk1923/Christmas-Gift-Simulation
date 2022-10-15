package strategy;

import entities.Child;

import java.util.ArrayList;

public final class NiceScoreStrategy implements OrderChildrenStrategy {
    @Override
    public void orderChildren(final ArrayList<Child> children) {
        // order by child average score
        children.sort((o1, o2) -> {
            int compare = Double.compare(o2.getAverageNiceScore(), o1.getAverageNiceScore());
            if (compare == 0) {
                compare = o1.getId() - o2.getId();
            }
            return compare;
        });
    }
}
