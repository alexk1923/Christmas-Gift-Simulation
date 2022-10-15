package database;

import fileio.AnnualChange;
import fileio.DataList;

import java.util.ArrayList;

public final class Database {
    private int numberOfYears;
    private double santaBudget;
    private DataList initialData;
    private ArrayList<AnnualChange> annualChanges;

    public ArrayList<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final ArrayList<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public DataList getInitialData() {
        return initialData;
    }

    public void setInitialData(final DataList initialData) {
        this.initialData = initialData;
    }


}
