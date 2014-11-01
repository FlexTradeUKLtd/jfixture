package com.flextrade.jfixture.specifications;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestClassRequestSpecification {

    private TypeRequestSpecification specification;

    @Before
    public void initialise() {
        this.specification = new TypeRequestSpecification();
    }

    @Test
    public void returns_false_if_request_is_not_instance_of_class() {
        assertFalse(this.specification.isSatisfiedBy("string"));
    }

    @Test
    public void returns_true_if_request_is_instance_of_class() {
        assertTrue(this.specification.isSatisfiedBy(String.class));
    }
}
