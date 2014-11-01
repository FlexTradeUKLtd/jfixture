package com.flextrade.jfixture.utility;

public class KeyValuePair {
    private final Object key;
    private final Object value;

    public KeyValuePair(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return this.key;
    }

    public Object getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyValuePair that = (KeyValuePair) o;

        return key.equals(that.key) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
