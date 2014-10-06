package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

import java.util.UUID;

class UuidGenerator implements SpecimenBuilder {
    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!request.equals(UUID.class)) {
            return new NoSpecimen();
        }

        return UUID.randomUUID();
    }
}
