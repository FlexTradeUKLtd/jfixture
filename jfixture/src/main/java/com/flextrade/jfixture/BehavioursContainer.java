package com.flextrade.jfixture;

public interface BehavioursContainer {
    void add(FixtureBehaviour behaviour);
    boolean remove(Class<? extends FixtureBehaviour> classToRemove);
    <T extends FixtureBehaviour> T find(Class<T> clazz);
}
