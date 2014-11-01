package com.flextrade.jfixture;

public interface BehavioursContainer {
    public void add(FixtureBehaviour behaviour);
    public boolean remove(Class<? extends FixtureBehaviour> classToRemove);
    public <T extends FixtureBehaviour> T find(Class<T> clazz) ;
}
