package com.flextrade.jfixture;

// Because {null} can be a valid specimen
// we need to use a special type to signal
// that the specimen builder can't deal
// with the request
public class NoSpecimen {
    public NoSpecimen() {
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NoSpecimen;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
