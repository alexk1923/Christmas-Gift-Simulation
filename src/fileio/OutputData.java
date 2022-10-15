package fileio;


import java.util.ArrayList;

public final class OutputData {
    private ArrayList<ChildOutputList> annualChildren = new ArrayList<>();

    public ArrayList<ChildOutputList> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(final ArrayList<ChildOutputList> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
