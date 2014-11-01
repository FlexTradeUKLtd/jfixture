package com.flextrade.jfixture.extensions;

public interface CreateExtensions {

    public <T extends Comparable<T>> T inRange(Class<T> clazz, T min, T max);

    public <T> T fromList(T... list);
}
