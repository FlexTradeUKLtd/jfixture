package com.flextrade.jfixture;

import java.lang.reflect.Constructor;
import java.util.List;

public interface ConstructorQuery {

    public List<Constructor<?>> getConstructorsForClass(Class<?> clazz);
}
