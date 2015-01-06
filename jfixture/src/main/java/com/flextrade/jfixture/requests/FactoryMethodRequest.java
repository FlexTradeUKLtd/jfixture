package com.flextrade.jfixture.requests;

import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Method;

public class FactoryMethodRequest {
    private final Method method;
    private final SpecimenType containingType;

    public FactoryMethodRequest(Method method, SpecimenType containingType) {
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

        FactoryMethodRequest that = (FactoryMethodRequest) o;

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
        return containingType + " " + formattedName();
    }

    private String formattedName() {
        StringBuilder sb = new StringBuilder();

        sb.append(method.getName()).append("(");
        Class[] params = method.getParameterTypes();
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i].getName());
            if (i < (params.length - 1))
                sb.append(",");
        }
        sb.append(")");

        return sb.toString();
    }
}
