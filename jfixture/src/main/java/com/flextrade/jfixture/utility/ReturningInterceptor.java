package com.flextrade.jfixture.utility;

public interface ReturningInterceptor<T> {
    T intercept(T instance);
}
