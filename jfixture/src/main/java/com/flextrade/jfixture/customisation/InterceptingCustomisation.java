package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.intercept.InterceptBehaviour;
import com.flextrade.jfixture.utility.Interceptor;

public class InterceptingCustomisation<T> implements Customisation {

    private final Class<T> classToIntercept;
    private final Interceptor<T> interceptor;

    public InterceptingCustomisation(Class<T> classToIntercept, Interceptor<T> interceptor) {
        this.classToIntercept = classToIntercept;
        this.interceptor = interceptor;
    }

    @Override
    public void customise(JFixture fixture) {
        fixture.behaviours().add(new InterceptBehaviour<T>(classToIntercept, interceptor));
    }
}
