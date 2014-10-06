package com.flextrade.jfixture.utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PrimitiveTypeMap {

    public static final Map<Class, Class> map;

    static {
        Map<Class, Class> mutableMap = new HashMap<Class, Class>();
        mutableMap.put(int.class, Integer.class);
        mutableMap.put(long.class, Long.class);
        mutableMap.put(double.class, Double.class);
        mutableMap.put(float.class, Float.class);
        mutableMap.put(boolean.class, Boolean.class);
        mutableMap.put(char.class, Character.class);
        mutableMap.put(byte.class, Byte.class);
        mutableMap.put(short.class, Short.class);
        map = Collections.unmodifiableMap(mutableMap);
    }

}
