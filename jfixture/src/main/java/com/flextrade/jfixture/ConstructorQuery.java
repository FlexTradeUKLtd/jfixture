package com.flextrade.jfixture;

import java.lang.reflect.Constructor;
import java.util.List;

public interface ConstructorQuery {

    List<Constructor<?>> getConstructorsForClass(Class<?> clazz);
}
