package com.flextrade.jfixture.specifications;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestSpecificClassSpecification {

    private SpecificTypeSpecification specification;
    private Class<String> specificClass;

    @Before
    public void initialise() {
        this.specificClass = String.class;
        this.specification = new SpecificTypeSpecification(this.specificClass);
    }

    @Test
    public void Null_request_returns_false() {
        boolean isSatisfied = this.specification.isSatisfiedBy(null);
        assertFalse(isSatisfied);
    }
    @Test
    public void Request_of_non_class_type_returns_false() {
        boolean isSatisfied = this.specification.isSatisfiedBy("string");
        assertFalse(isSatisfied);
    }

    @Test
    public void Request_of_different_type_returns_false() {
        boolean isSatisfied = this.specification.isSatisfiedBy(int.class);
        assertFalse(isSatisfied);
    }

    @Test
    public void Request_of_same_type_returns_true() {
        boolean isSatisfied = this.specification.isSatisfiedBy(this.specificClass);
        assertTrue(isSatisfied);
    }
}
