package net.fhannes.rx.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains static methods to convert any supported collection to a reactive collection.
 */
public final class RxCollections {

    /**
     * This is a utility class with static methods, it should not be instantiated.
     */
    private RxCollections() {

    }

    /**
     * Creates a reactive list, wrapped around the given list.
     *
     * @param list A given list.
     * @param <E> The type of the elements stored in the given list.
     * @return A reactive {@link ObservableList<E>} instance.
     */
    public static <E> ObservableList<E> of(List<E> list) {
        if (list instanceof ArrayList) {
            return new ObservableArrayList<>((ArrayList<E>) list);
        } else if (list instanceof LinkedList) {
            return new ObservableLinkedList<>((LinkedList<E>) list);
        }
        return new ObservableList<>(list);
    }

}
