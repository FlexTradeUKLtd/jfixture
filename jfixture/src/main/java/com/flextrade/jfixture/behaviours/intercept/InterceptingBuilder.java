package com.flextrade.jfixture.behaviours.intercept;

import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.Interceptor;

import java.lang.reflect.Type;

public class InterceptingBuilder<T> implements SpecimenBuilder {

    private final SpecimenBuilder builder;
    private final Class<T> classToIntercept;
    private final Interceptor<T> interceptor;

    public InterceptingBuilder(SpecimenBuilder builder, Class<T> classToIntercept, Interceptor<T> interceptor) {
        this.builder = builder;
        this.classToIntercept = classToIntercept;
        this.interceptor = interceptor;
    }

    @Override
    public Object create(Object request, SpecimenContext context) {
        Object specimen = this.builder.create(request, context);

        if(requestIsForAType(request) && specimenIsOfInterceptedType(specimen)) {
            interceptor.intercept((T) specimen);
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
