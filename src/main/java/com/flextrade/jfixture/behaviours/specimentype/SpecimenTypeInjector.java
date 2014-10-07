package com.flextrade.jfixture.behaviours.specimentype;

import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Type;

public class SpecimenTypeInjector implements SpecimenBuilder {

    private final SpecimenBuilder builder;

    public SpecimenTypeInjector(SpecimenBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Object create(final Object request, SpecimenContext context) {
        Object actualRequest;
        if(request instanceof Type) {
            actualRequest = SpecimenType.of((Type) request);
        } else {
            actualRequest = request;
        }

        return builder.create(actualRequest, context);
    }
}
