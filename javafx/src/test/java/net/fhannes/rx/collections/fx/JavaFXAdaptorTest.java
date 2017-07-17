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
package net.fhannes.rx.collections.fx;

import net.fhannes.rx.collections.ObservableList;
import net.fhannes.rx.collections.RxCollections;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Test unit for the {@link ObservableList} class.
 */
public class JavaFXAdaptorTest {

    @Test
    public void adapt() throws Exception {
        ObservableList<String> origList = RxCollections.of(new ArrayList<>());
        javafx.collections.ObservableList<String> list = JavaFXAdaptor.adapt(origList);
        String str = "Hello world!";
        origList.add(str);
        assertEquals(list.get(0), str);
    }

}