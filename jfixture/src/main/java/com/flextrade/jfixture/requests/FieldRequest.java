package com.flextrade.jfixture.requests;

import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Field;

public class FieldRequest {
    private final Field field;
    private final SpecimenType containingType;

    public FieldRequest(Field field, SpecimenType containingType) {
        this.field = field;
        this.containingType = containingType;
    }

    public Field getField() {
        return this.field;
    }

    public SpecimenType getContainingType() {
        return this.containingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldRequest that = (FieldRequest) o;

        return containingType.equals(that.containingType) && field.equals(that.field);
    }

    @Override
    public int hashCode() {
        int result = field.hashCode();
        result = 31 * result + containingType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return field.getType().getName() + " " + field.getName();
    }
}
