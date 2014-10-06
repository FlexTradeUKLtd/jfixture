package com.flextrade.jfixture.specifications;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;

public class TestNeverSpecification {

    @Test
    public void always_returns_false() {
        NeverSpecification neverPropertySpecification = new NeverSpecification();

        assertFalse(neverPropertySpecification.isSatisfiedBy("string"));
        assertFalse(neverPropertySpecification.isSatisfiedBy(10));
        assertFalse(neverPropertySpecification.isSatisfiedBy(String.class.getConstructors()[0]));
        assertFalse(neverPropertySpecification.isSatisfiedBy(Date.class));
        assertFalse(neverPropertySpecification.isSatisfiedBy(String.class));
        assertFalse(neverPropertySpecification.isSatisfiedBy(Integer.class));
    }
}
