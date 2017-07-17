package net.fhannes.rx.collections;

import io.reactivex.observers.TestObserver;
import net.fhannes.rx.collections.util.Indexed;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test unit for the {@link ObservableList} class.
 */
public class ObservableListTest {

    @Test
    public void add() throws Exception {
        ObservableList<Integer> list = RxCollections.of(new ArrayList<>());
        TestObserver<Integer> o = list.onAdded().map(Indexed::getValue).test();

        Integer value = 5;
        list.add(value);

        o.assertNoErrors();
        o.assertValue(value);
    }

}