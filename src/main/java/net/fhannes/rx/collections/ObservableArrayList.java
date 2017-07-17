package net.fhannes.rx.collections;

import java.util.ArrayList;

public class ObservableArrayList<E> extends ObservableList<E> {

    public ObservableArrayList() {
        this(new ArrayList<>());
    }

    public ObservableArrayList(ArrayList<E> list) {
        super(list);
    }

    @Override
    public void clear() {
        while (!getList().isEmpty()) {
            // By removing last element, the internal array can just be resized
            remove(getList().size() - 1);
        }
    }

}
