package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

import java.util.UUID;

class StringGenerator implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
       if (!request.equals(String.class)) {
            return new NoSpecimen();
       }

        return UUID.randomUUID().toString();
    }
}
