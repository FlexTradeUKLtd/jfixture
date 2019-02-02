package com.flextrade.jfixture.utility;

import java.lang.reflect.Type;
import java.util.List;

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


    public interface GenericTypeCreator {
        GenericType createGenericType(Type type, String name, List<Type> recursiveGenericsGuard);
    }

    public static class GenericTypeCreatorImpl implements GenericTypeCreator {
        @Override
        public GenericType createGenericType(Type type, String name, List<Type> recursiveGenericsGuard) {
            return new GenericType(SpecimenType.of(type, recursiveGenericsGuard), name);
        }
    }

    public static class GenericTypeCreatorWithGenericContextImpl implements GenericTypeCreator {
        private final SpecimenType contextType;

        public GenericTypeCreatorWithGenericContextImpl(SpecimenType contextType) {
            this.contextType = contextType;
        }

        @Override
        public GenericType createGenericType(Type type, String name, List<Type> recursiveGenericsGuard) {
            return new GenericType(SpecimenType.withGenericContext(type, contextType), name);
        }
    }
}
