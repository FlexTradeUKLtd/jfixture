package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;

import java.util.ArrayList;

class IterableBuilder implements SpecimenBuilder {
    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType specimenType = (SpecimenType) request;
        if (!Iterable.class.isAssignableFrom(specimenType.getRawType())) {
            return new NoSpecimen();
        }

        if (!specimenType.getRawType().isInterface()) {
            return new NoSpecimen();
        }

        return new ArrayList();
    }
}
