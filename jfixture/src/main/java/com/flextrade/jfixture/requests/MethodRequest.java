package com.flextrade.jfixture.requests;

import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Method;

public class MethodRequest {
    private final Method method;
    private final SpecimenType containingType;

    public MethodRequest(Method method, SpecimenType containingType) {
        this.method = method;
        this.containingType = containingType;
    }

    public Method getMethod() {
        return this.method;
    }

    public SpecimenType getContainingType() {
        return this.containingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodRequest that = (MethodRequest) o;

        return containingType.equals(that.containingType) && method.equals(that.method);
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + containingType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MethodRequest{" +
                "method=" + method +
                ", containingType=" + containingType +
                '}';
    }
}
