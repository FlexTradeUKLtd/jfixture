package com.flextrade.jfixture.utility;

import java.util.Arrays;
import java.util.HashMap;

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

    public String getNameFromType(SpecimenType type) {
        for(GenericType gt : underlying) {
            if(gt.getType().equals(type))
                return gt.getName();
        }

        return "";
    }

    public static GenericTypeCollection empty() {
        return new GenericTypeCollection(new GenericType[0]);
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericTypeCollection that = (GenericTypeCollection) o;

        return length == that.length && nameTypeMap.equals(that.nameTypeMap) && Arrays.equals(underlying, that.underlying);
    }

    @Override
    public int hashCode() {
        int result = nameTypeMap.hashCode();
        result = 31 * result + Arrays.hashCode(underlying);
        result = 31 * result + length;
        return result;
    }
}
