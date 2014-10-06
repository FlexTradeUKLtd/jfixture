package com.flextrade.jfixture;

import java.lang.reflect.Type;

public interface SpecimenCreator {
    public <T> T create(Type clazz);
}
