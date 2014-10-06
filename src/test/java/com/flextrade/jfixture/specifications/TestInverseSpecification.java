package com.flextrade.jfixture.specifications;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class TestInverseSpecification {

    private InverseSpecification specification;
    private final Object request = new Object();

    @Mock
    private Specification mockInnerSpecification;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.specification = new InverseSpecification(this.mockInnerSpecification);
    }

    @Test
    public void returns_false_if_inner_specification_returns_true() {
        when(this.mockInnerSpecification.isSatisfiedBy(this.request)).thenReturn(true);
        boolean isSatisfied = this.specification.isSatisfiedBy(this.request);
        assertFalse(isSatisfied);
    }

    @Test
    public void returns_true_if_inner_specification_returns_false() {
        when(this.mockInnerSpecification.isSatisfiedBy(this.request)).thenReturn(false);
        boolean isSatisfied = this.specification.isSatisfiedBy(this.request);
        assertTrue(isSatisfied);
    }
}
