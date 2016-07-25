package com.flextrade.jfixture;

import com.flextrade.jfixture.ConstructorQuery;

import java.lang.reflect.Constructor;
import java.util.*;

public class PackageProtectedConstructorsQuery implements ConstructorQuery {

    private final Comparator<Constructor<?>> comparator;

    public PackageProtectedConstructorsQuery() {
        this.comparator = null;
    }

    public PackageProtectedConstructorsQuery(Comparator<Constructor<?>> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<Constructor<?>> getConstructorsForClass(Class<?> clazz) {
        List<Constructor<?>> constructors = Arrays.asList(clazz.getDeclaredConstructors());
        List<Constructor<?>> packageProtectedConstructors = new ArrayList<Constructor<?>>();
        for(Constructor<?> ctor : constructors) {
            if(ctor.getModifiers() == 0) {
                packageProtectedConstructors.add(ctor);
            }
        }

        if (this.comparator != null) {  // Don't attempt to sort if there's no Comparator
            Collections.sort(packageProtectedConstructors, this.comparator);
        }

        return packageProtectedConstructors;
    }
}
