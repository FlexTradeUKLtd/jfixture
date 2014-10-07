package com.flextrade.jfixture.requests;

import java.lang.reflect.Type;

public class KeyValueRequest {
    private final Type keyType;
    private final Type valueType;

    public KeyValueRequest(Type keyType, Type valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    public Type getKeyType() {
        return this.keyType;
    }

    public Type getValueType() {
        return this.valueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyValueRequest that = (KeyValueRequest) o;

        return keyType.equals(that.keyType) && valueType.equals(that.valueType);
    }

    @Override
    public int hashCode() {
        int result = keyType.hashCode();
        result = 31 * result + valueType.hashCode();
        return result;
    }
}
