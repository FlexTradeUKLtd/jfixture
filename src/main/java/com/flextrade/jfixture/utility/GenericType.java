package com.flextrade.jfixture.utility;

public class GenericType {

    private final SpecimenType type;
    private final String name;

    public GenericType(SpecimenType type, String name) {
        this.type = type;
        this.name = name;
    }

    public SpecimenType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericType that = (GenericType) o;

        return name.equals(that.name) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}