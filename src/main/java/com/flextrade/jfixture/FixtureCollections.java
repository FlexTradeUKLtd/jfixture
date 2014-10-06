package com.flextrade.jfixture;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class FixtureCollections {

    private final SpecimenCreator specimenCreator;
    private final MultipleCount multipleCount;

    public <T> void addManyTo(Collection<T> collection, Class<T> clazz) {
        this.addManyTo(collection, clazz, this.multipleCount.getCount());
    }

    public <T> void addManyTo(Collection<T> collection, Class<T> clazz, int count) {
        for (int i = 0; i < count; i++) {

            Object result = this.specimenCreator.create(clazz);
            if (result instanceof NoSpecimen) continue;

            collection.add((T) result);
        }
    }

    public <T, U> void addManyTo(Map<T, U> map, Class<T> keyType, Class<U> valueType) {
        this.addManyTo(map, keyType, valueType, this.multipleCount.getCount());
    }

    public <T, U> void addManyTo(Map<T, U> map, Class<T> keyType, Class<U> valueType, int count) {
        for (int i = 0; i < count; i++) {

            Object key = this.specimenCreator.create(keyType);
            Object value = this.specimenCreator.create(valueType);

            map.put((T)key, (U)value);
        }
    }

    public <T> Collection<T> createCollection(Class<T> innerType) {
        return this.createCollection(innerType, this.multipleCount.getCount());
    }

    public <T> Collection<T> createCollection(Class<T> innerType, int size) {
        return this.createCollection(Collection.class, innerType, size);
    }

    public <T extends Collection<U>, U> T createCollection(Class<T> collectionType, Class<U> innerType) {
        return this.createCollection(collectionType, innerType, this.multipleCount.getCount());
    }

    public <T extends Collection<U>, U> T createCollection(Class<T> collectionType, Class<U> innerType, int size) {
        T collection = this.specimenCreator.create(collectionType);
        this.addManyTo(collection, innerType, size);

        return collection;
    }

    public <T, U> Map<T, U> createMap(Class<T> keyType, Class<U> valueType) {
        return this.createMap(keyType, valueType, this.multipleCount.getCount());
    }

    public <T, U> Map<T, U> createMap(Class<T> keyType, Class<U> valueType, int size) {
        return this.createMap(Map.class, keyType, valueType, size);
    }

    public <T extends Map<U, V>, U, V> T createMap(Class<T> mapType, Class<U> keyType, Class<V> valueType) {
        return this.createMap(mapType, keyType, valueType, this.multipleCount.getCount());
    }

    public <T extends Map<U, V>, U, V> T createMap(Class<T> mapType, Class<U> keyType, Class<V> valueType, int size) {
        T map = this.specimenCreator.create(mapType);
        this.addManyTo(map, keyType, valueType, size);

        return map;
    }
}
