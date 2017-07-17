# Reactive Collections for RxJava

RxJavaCollections is a library which offers extensions for the [RxJava](https://github.com/ReactiveX/RxJava/) library, in order to observe operations performed on standard Java collections such as `List<E>`.

This project is still under development and currently only offers support for the `List<E>` class.

## Getting started

In order to observe actions performed on a `List<E>`, you can wrap an object of that type as `RxCollections.of(list)`. This new object of type `ObservableList<E>` has various methods which return an `Observable<Indexed<E>` object. One such method is `onAdded()`, which returns an observable that emits a value whenever an element is added to the list. The value emitted is of type `Indexed<E>` and contains the element added, as well as the index it was added at in the list. These values can be obtained with `getValue()` and `getIndex()` respectively.

## Motivation

Creating this library was motivated by the fact that RxJava does not come with built-in support for this type of reactive collections. The reason for this is that collections like these are more akin to imperative programming, rather than the pure reactive methodology adopted by the ReactiveX framework on which RxJava is based.

In an imperative language such as Java, it can be beneficial to mix both the imperative and reactive strategies, especially when dealing with user interfaces. A possible downside of both the [ReactFX](https://github.com/TomasMikula/ReactFX) and [RXJavaFX](https://github.com/ReactiveX/RxJavaFX), depending on the project they are used on, is that they have a fixed dependency on JavaFX. While both frameworks offer reactive collections (from JavaFX), similar to those available in this library, the dependency on JavaFX may inhibit the portability of projects to platforms where JavaFX is not available.

## Disclaimer

The reactive wrapper classes in this library incur a performance penalty, especially when performing operations with other collections, such as `addAll()`, because it will handle each element of these collections individually and emit them to the observables after each individual element has been handled.

To improve performance, the `clear()` method for the `ArrayList<E>` wrapper class `ObservableArrayList<E>` will remove and emit removals starting at the end of the list, rather than at the start.

## License

Copyright (c) 2017, Frédéric Hannes.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.