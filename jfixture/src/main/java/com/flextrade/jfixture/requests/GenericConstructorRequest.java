package com.flextrade.jfixture.requests;

import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Constructor;

public class GenericConstructorRequest {
    private final Constructor constructor;
    private final SpecimenType containingType;

    public GenericConstructorRequest(Constructor constructor, SpecimenType containingType) {
        this.constructor = constructor;
        this.containingType = containingType;
    }

    public Constructor getConstructor() {
        return this.constructor;
    }

    public SpecimenType getContainingType() {
        return this.containingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericConstructorRequest that = (GenericConstructorRequest) o;

        return constructor.equals(that.constructor) && containingType.equals(that.containingType);
    }

    @Override
    public int hashCode() {
        int result = constructor.hashCode();
        result = 31 * result + containingType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GenericConstructorRequest{" +
                "constructor=" + constructor +
                ", containingType=" + containingType +
                '}';
    }
}
