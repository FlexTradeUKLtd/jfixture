package com.flextrade.jfixture.utility;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public abstract class SpecimenType<T> implements Type {

    private static final String typeNamePrefix = "SpecimenType.";
    private final Class rawType;
    private final GenericTypeCollection genericTypeArguments;

    protected SpecimenType() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if(!(genericSuperclass instanceof ParameterizedType)) {
            throw new IllegalArgumentException("No generic type argument provided");
        }
        
        ParameterizedType pt = (ParameterizedType)genericSuperclass;
        SpecimenTypeFields fields = getFields(pt.getActualTypeArguments()[0]);
        this.rawType = fields.rawType;
        this.genericTypeArguments = fields.genericTypeArguments;
    }

    SpecimenType(Type type) {
        SpecimenTypeFields fields = getFields(type);
        this.rawType = fields.rawType;
        this.genericTypeArguments = fields.genericTypeArguments;
    }

    SpecimenType(Type type, GenericTypeCollection genericTypeArguments) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            this.rawType = nonPrimitiveType(pt.getRawType());
            this.genericTypeArguments = genericTypeArguments;
            return;
        }

        SpecimenTypeFields fields = getFields(type);
        this.rawType = fields.rawType;
        this.genericTypeArguments = fields.genericTypeArguments;
    }

    public static SpecimenType<?> of(Type type) {
        return new SpecimenType<Object>(type){};
    }

    public static <T> SpecimenType<T> of(Class<T> clazz) {
        return new SpecimenType<T>(clazz){};
    }

    private static SpecimenTypeFields getFields(Type type) {
        if(type instanceof SpecimenType) return getSpecimenTypeFields((SpecimenType) type);
        if(type instanceof Class) return getClassTypeFields(type);
        if(type instanceof ParameterizedType) return getParameterizedTypeFields((ParameterizedType) type);
        if(type instanceof GenericArrayType) return getGenericArrayFields((GenericArrayType) type);

        else if(type instanceof WildcardType)
            throw new UnsupportedOperationException("Wildcard types not supported");

        throw new UnsupportedOperationException(String.format("Unknown Type : %s", type));
    }

    public final Class getRawType() {
        return this.rawType;
    }

    public final GenericTypeCollection getGenericTypeArguments() {
        return this.genericTypeArguments;
    }

    private static GenericTypeCollection createGenericTypeNameMap(ParameterizedType parameterizedType) {
        Class<?> rawType = (Class) parameterizedType.getRawType();

        Type[] genericArguments = parameterizedType.getActualTypeArguments();
        TypeVariable[] typeParameters = rawType.getTypeParameters();
        GenericType[] genericTypes = new GenericType[genericArguments.length];
        for (int i = 0; i < genericArguments.length; i++) {
            genericTypes[i] = new GenericType(SpecimenType.of(genericArguments[i]), typeParameters[i].getName());
        }

        return new GenericTypeCollection(genericTypes);
    }

    private static SpecimenTypeFields getGenericArrayFields(GenericArrayType type) {
        SpecimenTypeFields fields = new SpecimenTypeFields();
        Type componentType = type.getGenericComponentType();
        fields.rawType = Array.newInstance(SpecimenType.of(componentType).getRawType(), 0).getClass();
        fields.genericTypeArguments = GenericTypeCollection.empty();
        return fields;
    }

    private static SpecimenTypeFields getParameterizedTypeFields(ParameterizedType type) {
        SpecimenTypeFields fields = new SpecimenTypeFields();
        fields.rawType = nonPrimitiveType(type.getRawType());
        fields.genericTypeArguments = createGenericTypeNameMap(type);
        return fields;
    }

    private static SpecimenTypeFields getClassTypeFields(Type type) {
        SpecimenTypeFields fields = new SpecimenTypeFields();
        fields.rawType = nonPrimitiveType(type);
        fields.genericTypeArguments = GenericTypeCollection.empty();
        return fields;
    }

    private static SpecimenTypeFields getSpecimenTypeFields(SpecimenType type) {
        SpecimenTypeFields fields = new SpecimenTypeFields();
        fields.rawType = type.rawType;
        fields.genericTypeArguments = type.genericTypeArguments;
        return fields;
    }

    @Override
    public final int hashCode() {
        return this.rawType.hashCode() ^ this.genericTypeArguments.hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        if(!(obj instanceof Type)) return false;

        SpecimenType other;
        if(!(obj instanceof SpecimenType)) {
            other = SpecimenType.of((Type) obj);
        } else {
            other = (SpecimenType)obj;
        }

        return this.rawType.equals(other.rawType) && this.genericTypeArguments.equals(other.genericTypeArguments);
    }

    private static Class nonPrimitiveType(Type type) {
        Class clazz = (Class)type;
        if(!(clazz.isPrimitive())) return (Class)type;
        return PrimitiveTypeMap.map.get(clazz);
    }

    @Override
    public final String toString() {
        if(this.genericTypeArguments.getLength() == 0) {
            return typeNamePrefix + justClassName(this.rawType);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(typeNamePrefix);
        sb.append(justClassName(this.rawType));
        sb.append("<");
        for(int i = 0; i < this.genericTypeArguments.getLength(); i++) {
            sb.append(justClassName(this.genericTypeArguments.get(i).getType()));
            if(i < this.genericTypeArguments.getLength() -1)
                sb.append(", ");
        }
        sb.append(">");
        return sb.toString();
    }

    private static String justClassName(Type type) {
        if(type instanceof SpecimenType) return type.toString();
        if(!(type instanceof Class)) throw new RuntimeException("This shouldn't happen");

        Class clazz =(Class)type;
        return clazz.getSimpleName();
    }

    private static class SpecimenTypeFields {
        public Class rawType;
        public GenericTypeCollection genericTypeArguments;
    }
}
