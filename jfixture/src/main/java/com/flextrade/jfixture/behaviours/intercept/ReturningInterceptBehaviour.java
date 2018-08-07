package com.flextrade.jfixture.behaviours.intercept;

import com.flextrade.jfixture.FixtureBehaviour;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.utility.ReturningInterceptor;

public class ReturningInterceptBehaviour<T> implements FixtureBehaviour {

    private final Class<T> classToIntercept;
    private final ReturningInterceptor<T> interceptor;

    public ReturningInterceptBehaviour(Class<T> classToIntercept, ReturningInterceptor<T> interceptor) {
        this.classToIntercept = classToIntercept;
        this.interceptor = interceptor;
    }

    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {
        return new ReturningInterceptingBuilder<T>(builder, classToIntercept, interceptor);
    }
}
