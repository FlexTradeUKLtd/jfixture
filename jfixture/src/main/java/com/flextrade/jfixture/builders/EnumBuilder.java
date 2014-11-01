package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;

import java.util.HashMap;
import java.util.Map;

class EnumBuilder implements SpecimenBuilder {

    private final Map<Class, Integer> enumCountMap = new HashMap<Class, Integer>();

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType specimenType = (SpecimenType) request;
        Class requestClass = specimenType.getRawType();
        if (!(requestClass.isEnum())) {
            return new NoSpecimen();
        }

        ensureCountMapContainsEntry(requestClass);

        return getNextEnumValue(requestClass);
    }

    private Enum getNextEnumValue(Class requestClass) {
        Integer enumIndex = enumCountMap.get(requestClass);
        Enum[] enumConstants = (Enum[]) requestClass.getEnumConstants();
        Enum enumResult = enumConstants[enumIndex];

        enumIndex = enumIndex == enumConstants.length - 1 ? 0 : enumIndex + 1;
        enumCountMap.put(requestClass, enumIndex);
        return enumResult;
    }

    private void ensureCountMapContainsEntry(Class requestClass) {
        if (!enumCountMap.containsKey(requestClass)) {
            enumCountMap.put(requestClass, 0);
        }
    }
}
