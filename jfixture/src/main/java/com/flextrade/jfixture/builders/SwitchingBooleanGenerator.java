package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

class SwitchingBooleanGenerator implements SpecimenBuilder {

    private boolean value;

    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!request.equals(Boolean.class)) {
            return new NoSpecimen();
        }

        value = !value;
        return value;
    }
}
