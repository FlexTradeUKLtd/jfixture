package com.flextrade.jfixture.behaviours.intercept;

import com.flextrade.jfixture.FixtureBehaviour;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.utility.Interceptor;

public class InterceptBehaviour<T> implements FixtureBehaviour {

    private final Class<T> classToIntercept;
    private final Interceptor<T> interceptor;

    public InterceptBehaviour(Class<T> classToIntercept, Interceptor<T> interceptor) {
        this.classToIntercept = classToIntercept;
        this.interceptor = interceptor;
    }

    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {
        return new InterceptingBuilder<T>(builder, classToIntercept, interceptor);
    }
}
