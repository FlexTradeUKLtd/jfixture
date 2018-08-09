package com.flextrade.jfixture.utility;

public interface Transformer<T> {
    T intercept(T instance);
}
