package entities;

import enums.Category;
import enums.Cities;
import enums.ElvesType;
import fileio.ChildUpdate;
import observable.IChildObserver;
import observable.IObservable;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public final class Child implements IChildObserver {
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private Cities city;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private ArrayList<Double> niceScoreHistory = new ArrayList<>();
    private Double averageNiceScore;
    private int niceScoreBonus;
    private ElvesType elf;

    public Child() {

    }

    @Override
    public void update(final IObservable observable, final ArrayList<ChildUpdate> childrenUpdates) {
        // increase the age
        this.age++;

        if (childrenUpdates.size() == 0) {
            this.niceScore = null;
        }

        for (ChildUpdate childUpdate : childrenUpdates) {
            // if the child needs to be updated
            if (childUpdate.getId() == this.id) {
                if (childUpdate.getNiceScore() != null) {
                    this.niceScoreHistory.add(childUpdate.getNiceScore());
                }
                if (childUpdate.getGiftsPreferences() != null) {
                    // remove the duplicates and update gift preferences
                    LinkedHashSet<Category> uniqueGifts =
                            new LinkedHashSet<>(childUpdate.getGiftsPreferences());
                    uniqueGifts.addAll(this.giftsPreferences);
                    this.giftsPreferences = new ArrayList<>(uniqueGifts);
                }
                this.setElf(childUpdate.getElf());
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public int getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(final int niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public void setAverageNiceScore(final double averageNiceScore) {
        final int percentage = 100;
        final double perfectScore = 10.0;
        double averageNiceScoreWithBonus = averageNiceScore * this.niceScoreBonus / percentage;
        this.averageNiceScore = averageNiceScoreWithBonus <= perfectScore
                ? averageNiceScore : perfectScore;
    }

    public Double getAverageNiceScore() {
        return averageNiceScore;
    }

    public ElvesType getElf() {
        return elf;
    }

    public void setElf(final ElvesType elf) {
        this.elf = elf;
    }

    public double calcElfTotalBudget(final double initialBudget) {
        double finalBudget;
        final int amplifier = 30;
        final int percentage = 100;
        if (this.elf.equals(ElvesType.BLACK)) {
            finalBudget = initialBudget - initialBudget * amplifier / percentage;
            return finalBudget;
        } else if (this.elf.equals(ElvesType.PINK)) {
            finalBudget = initialBudget + initialBudget * amplifier / percentage;
        } else {
            finalBudget = initialBudget;
        }
        return finalBudget;
    }
}
