package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.CircularList;
import com.flextrade.jfixture.utility.SpecimenType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class EnumBuilder implements SpecimenBuilder {

    private final Map<Class, CircularList<Enum>> enumListMap = new HashMap<Class, CircularList<Enum>>();

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

        ensureListMapContainsList(requestClass);

        return getNextEnumValue(requestClass);
    }

    private Enum getNextEnumValue(Class requestClass) {
        return enumListMap.get(requestClass).next();
    }

    private void ensureListMapContainsList(Class requestClass) {
        if (!enumListMap.containsKey(requestClass)) {
            enumListMap.put(requestClass, new CircularList<Enum>(allEnumValues(requestClass)));
        }
    }

    private List<Enum> allEnumValues(Class<?> enumClass) {
        Enum[] enumConstants = (Enum[]) enumClass.getEnumConstants();
        List<Enum> allValues = Arrays.asList(enumConstants);
        Collections.shuffle(allValues);
        return allValues;
    }
}
