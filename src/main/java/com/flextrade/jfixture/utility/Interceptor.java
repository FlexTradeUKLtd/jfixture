package com.flextrade.jfixture.utility;

public interface Interceptor<T> {
    void intercept(T instance);
}
