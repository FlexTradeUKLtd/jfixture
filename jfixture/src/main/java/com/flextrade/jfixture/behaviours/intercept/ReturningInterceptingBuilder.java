package com.flextrade.jfixture.behaviours.intercept;

import java.lang.reflect.Type;

import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.ReturningInterceptor;

public class ReturningInterceptingBuilder<T> implements SpecimenBuilder {

    private final SpecimenBuilder builder;
    private final Class<T> classToIntercept;
    private final ReturningInterceptor<T> interceptor;

    public ReturningInterceptingBuilder(SpecimenBuilder builder, Class<T> classToIntercept, ReturningInterceptor<T> interceptor) {
        this.builder = builder;
        this.classToIntercept = classToIntercept;
        this.interceptor = interceptor;
    }

    @Override
    public Object create(Object request, SpecimenContext context) {
        Object specimen = this.builder.create(request, context);

        if (requestIsForAType(request) && specimenIsOfInterceptedType(specimen)) {
            specimen = interceptor.intercept((T) specimen);
        }

        return specimen;
    }

    private boolean specimenIsOfInterceptedType(Object specimen) {
        return specimen != null && specimen.getClass().equals(classToIntercept);
    }

    private boolean requestIsForAType(Object request) {
        return request instanceof Type;
    }
}
