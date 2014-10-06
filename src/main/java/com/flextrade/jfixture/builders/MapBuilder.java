package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;

import java.util.HashMap;
import java.util.Map;

class MapBuilder implements SpecimenBuilder {
    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType specimenType = (SpecimenType)request;
        Class requestClass = specimenType.getRawType();
        if (!Map.class.isAssignableFrom(requestClass)) {
            return new NoSpecimen();
        }

        if (!requestClass.isInterface()) {
            return new NoSpecimen();
        }

        return new HashMap();
    }
}
