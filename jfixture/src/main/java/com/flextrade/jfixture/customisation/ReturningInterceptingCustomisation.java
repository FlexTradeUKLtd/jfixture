package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.intercept.ReturningInterceptBehaviour;
import com.flextrade.jfixture.utility.ReturningInterceptor;

public class ReturningInterceptingCustomisation<T> implements Customisation {

    private final Class<T> classToIntercept;
    private final ReturningInterceptor<T> interceptor;

    public ReturningInterceptingCustomisation(Class<T> classToIntercept, ReturningInterceptor<T> interceptor) {
        this.classToIntercept = classToIntercept;
        this.interceptor = interceptor;
    }

    @Override
    public void customise(JFixture fixture) {
        fixture.behaviours().add(new ReturningInterceptBehaviour<T>(classToIntercept, interceptor));
    }
}
