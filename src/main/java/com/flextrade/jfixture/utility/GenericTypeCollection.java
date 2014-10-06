package com.flextrade.jfixture.utility;

import lombok.EqualsAndHashCode;

import java.util.HashMap;

@EqualsAndHashCode
public class GenericTypeCollection {

    private final HashMap<String, SpecimenType> nameTypeMap = new HashMap<String, SpecimenType>();
    private final GenericType[] underlying;
    private final int length;

    public GenericTypeCollection(GenericType[] genericTypes) {
        underlying = genericTypes;
        length = underlying.length;

        for (GenericType genericType : genericTypes) {
            nameTypeMap.put(genericType.getName(), genericType.getType());
        }
    }

    public GenericType get(int index) {
        return underlying[index];
    }

    public SpecimenType getType(String name) {
        return nameTypeMap.get(name);
    }

    public static GenericTypeCollection empty() {
        return new GenericTypeCollection(new GenericType[0]);
    }

    public int getLength() {
        return length;
    }
}
