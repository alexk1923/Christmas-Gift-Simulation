package fileio;

import java.util.ArrayList;

public final class ChildOutputList {
    private ArrayList<ChildOutput> children;

    public ChildOutputList(final ArrayList<ChildOutput> children) {
        this.children = children;
    }

    public ArrayList<ChildOutput> getChildren() {
        return children;
    }

    public void setChildren(final ArrayList<ChildOutput> children) {
        this.children = children;
    }

}
