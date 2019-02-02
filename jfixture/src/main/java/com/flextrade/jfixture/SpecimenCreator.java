package com.flextrade.jfixture;

import java.lang.reflect.Type;

public interface SpecimenCreator {
    <T> T create(Type clazz);
}
