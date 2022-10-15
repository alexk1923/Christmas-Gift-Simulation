package observable;

import fileio.ChildUpdate;

import java.util.ArrayList;

public interface IObservable {
    void add(IChildObserver observer);
    void remove(IChildObserver observer);
    void notifyObserver(ArrayList<ChildUpdate> childrenUpdates);
}
