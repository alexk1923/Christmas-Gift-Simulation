package observable;

import fileio.ChildUpdate;

import java.util.ArrayList;

public interface IChildObserver {
    void update(IObservable observable, ArrayList<ChildUpdate> childrenUpdates);
}
