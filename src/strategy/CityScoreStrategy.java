package strategy;

import entities.Child;
import enums.Cities;

import java.util.ArrayList;
import java.util.HashMap;

public final class CityScoreStrategy implements OrderChildrenStrategy {
    @Override
    public void orderChildren(final ArrayList<Child> children) {
        HashMap<Cities, ArrayList<Double>> citiesScore = this.getCitiesByScore(children);
        // order by city average score
        children.sort((o1, o2) -> {
            int compare = Double.compare(o2.getCity().getNiceScore(citiesScore), o1.getCity()
                    .getNiceScore(citiesScore));
            if (compare == 0) {
                compare = (o1.getCity().toString().compareTo(o2.getCity().toString()));
            }
            return compare;
        });
    }

    private HashMap<Cities, ArrayList<Double>> getCitiesByScore(final ArrayList<Child> children) {
        HashMap<Cities, ArrayList<Double>> citiesScores = new HashMap<>();
        for (Child child : children) {
            // if the city hasn't been added to the hash map
            if (!citiesScores.containsKey(child.getCity())) {
                ArrayList<Double> scores = new ArrayList<>();
                // add the average score for the current child
                scores.add(child.getAverageNiceScore());
                citiesScores.put(child.getCity(), scores);
            } else {
                // add a new average score in the list for current child's city
                ArrayList<Double> currentScores = citiesScores.get(child.getCity());
                currentScores.add(child.getAverageNiceScore());
                citiesScores.replace(child.getCity(), currentScores);
            }
        }
        return citiesScores;
    }
}
