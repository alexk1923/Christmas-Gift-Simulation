package fileio;

import entities.Child;
import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public final class ChildOutput {
    private int id;
    private String lastName;
    private String firstName;
    private Cities city;
    private int age;
    private ArrayList<Category> giftsPreferences;
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private double assignedBudget;
    private ArrayList<GiftOutput> receivedGifts;

    public ChildOutput(final Child baseChild, final double assignedBudget,
                       final ArrayList<GiftOutput> receivedGifts) {
        this.id = baseChild.getId();
        this.lastName = baseChild.getLastName();
        this.firstName = baseChild.getFirstName();
        this.city = baseChild.getCity();
        this.age = baseChild.getAge();
        this.giftsPreferences = new ArrayList<>(baseChild.getGiftsPreferences());
        this.averageScore = baseChild.getAverageNiceScore();
        this.niceScoreHistory = new ArrayList<>(baseChild.getNiceScoreHistory());
        this.assignedBudget = assignedBudget;
        this.receivedGifts = new ArrayList<>(receivedGifts);
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

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public ArrayList<GiftOutput> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final ArrayList<GiftOutput> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}
