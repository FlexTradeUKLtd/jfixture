package com.flextrade.jfixture;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DefaultConstructorQuery implements ConstructorQuery {

    private final Comparator<Constructor<?>> comparator;

    public DefaultConstructorQuery() {
        this.comparator = null;
    }

    public DefaultConstructorQuery(Comparator<Constructor<?>> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<Constructor<?>> getConstructorsForClass(Class<?> clazz) {
        List<Constructor<?>> constructors = Arrays.asList(clazz.getConstructors());

        if (this.comparator != null) {  // Don't attempt to sort if there's no Comparator
            Collections.sort(constructors, this.comparator);
        }

        return constructors;
    }
}
