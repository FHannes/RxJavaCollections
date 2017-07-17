package net.fhannes.rx.collections;

import java.util.ArrayList;

/**
 * This class is a reactive list. It is a specialized version of the {@link ObservableList<E>} class, which wraps around
 * an {@link ArrayList} object.
 *
 * @param <E> The type of elements stored in the list.
 */
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
