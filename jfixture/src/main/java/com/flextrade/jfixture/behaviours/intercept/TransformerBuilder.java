package com.flextrade.jfixture.behaviours.intercept;

import java.lang.reflect.Type;

import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.Transformer;

public class TransformerBuilder<T> implements SpecimenBuilder {

    private final SpecimenBuilder builder;
    private final Class<T> classToIntercept;
    private final Transformer<T> transformer;

    public TransformerBuilder(SpecimenBuilder builder, Class<T> classToIntercept, Transformer<T> transformer) {
        this.builder = builder;
        this.classToIntercept = classToIntercept;
        this.transformer = transformer;
    }

    @Override
    public Object create(Object request, SpecimenContext context) {
        Object specimen = this.builder.create(request, context);

        if (requestIsForAType(request) && specimenIsOfInterceptedType(specimen)) {
            specimen = transformer.intercept((T) specimen);
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
