/*
 * This file is part of the RxJavaCollections library.
 * https://github.com/FHannes/RxJavaCollections
 *
 * Copyright (c) 2017, Frédéric Hannes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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