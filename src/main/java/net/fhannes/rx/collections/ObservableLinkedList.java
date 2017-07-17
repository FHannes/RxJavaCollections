package net.fhannes.rx.collections;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class is a reactive list. It is a specialized version of the {@link ObservableList<E>} class, which wraps around
 * an {@link LinkedList} object.
 *
 * @param <E> The type of elements stored in the list.
 */
public class ObservableLinkedList<E> extends ObservableList<E> {

    public ObservableLinkedList() {
        this(new LinkedList<>());
    }

    public ObservableLinkedList(LinkedList<E> list) {
        super(list);
    }

}
