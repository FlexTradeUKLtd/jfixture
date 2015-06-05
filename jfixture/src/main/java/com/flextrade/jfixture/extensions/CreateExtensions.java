package com.flextrade.jfixture.extensions;

public interface CreateExtensions {

    <T, U extends Comparable<U>>  T inRange(Class<T> clazz, U min, U max);

    <T> T fromList(T... list);
}
