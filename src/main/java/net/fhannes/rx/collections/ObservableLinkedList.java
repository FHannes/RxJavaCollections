package net.fhannes.rx.collections;

import java.util.LinkedList;

public class ObservableLinkedList<E> extends ObservableList<E> {

    public ObservableLinkedList() {
        this(new LinkedList<>());
    }

    public ObservableLinkedList(LinkedList<E> list) {
        super(list);
    }

}
