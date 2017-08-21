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

    /**
     *  Combines the generic type mappings of this GenericTypeCollection with the generic type mappings of the {@code other} GenericTypeCollection.
     *  <p>
     *  <b>Use case:</b> when a non generic Class {@code Foo} extends a generic type {@code Bar<T>}, example:
     *  <p>
     *  <code>Class Foo extends Bar&lt;String&gt;</code>
     *  <p>
     *  The generic type mappings for Foo (none in this example) can then be combined with the generic type mappings for {@code Bar<String>}
     *  <p>
     *  If {@code other} contains an existing mapping in {@code this} then that mapping will be replaced (i.e. the mapping from {@code other} will be used).
     *
     * @param other the other GenericTypeCollection to combine with
     * @return a new GenericTypeCollection representing a union of {@code this} and {@code other}
     */
    public GenericTypeCollection combineWith(GenericTypeCollection other) {
        int firstLen = this.underlying.length;
        int secondLen = other.underlying.length;
        GenericType[] combined = new GenericType[this.underlying.length + other.underlying.length];
        System.arraycopy(this.underlying, 0, combined, 0, firstLen);
        System.arraycopy(other.underlying, 0, combined, firstLen, secondLen);
        return new GenericTypeCollection(combined);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericTypeCollection that = (GenericTypeCollection) o;
        if (underlying.length != that.underlying.length) return false;

        for (int i = 0; i < underlying.length; i++) {
            if (!underlying[i].getType().equals(that.underlying[i].getType())) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameTypeMap.hashCode();
        result = 31 * result + Arrays.hashCode(underlying);
        result = 31 * result + length;
        return result;
    }

    @Override
    public String toString() {
        return nameTypeMap.toString();
    }
}
