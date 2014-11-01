package com.flextrade.jfixture.requests.enrichers;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class NumberListBuilder {

    public List<Object> getList(double[] numbers, Type requestType) {
        List<Object> objectList = new ArrayList<Object>();
        for (double doubleValue : numbers) {
            Object actualNumber = toActualNumber(doubleValue, requestType);
            objectList.add(actualNumber);
        }

        return objectList;
    }

    // Annotations can only contain primitives so I've chosen
    // double[] as the API. This means we need to convert each
    // double into the actual type of the field. It could go wrong
    // but I'm going to rely on the user to not do anything silly
    private Object toActualNumber(Double value, Type type) {
        if (type.equals(Byte.class)) return value.byteValue();
        if (type.equals(Short.class)) return value.shortValue();
        if (type.equals(Integer.class)) return value.intValue();
        if (type.equals(Long.class)) return value.longValue();
        if (type.equals(Float.class)) return value.floatValue();
        if (type.equals(Double.class)) return value;
        if (type.equals(BigInteger.class)) return BigInteger.valueOf(value.longValue());
        if (type.equals(BigDecimal.class)) return BigDecimal.valueOf(value);

        return value;
    }
}
