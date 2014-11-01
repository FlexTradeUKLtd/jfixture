package com.flextrade.jfixture.specifications;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class TestAllSatisfiedCompositePropertySpecification {

    private AllSatisfiedCompositePropertySpecification compositeSpecification;
    private Object request;

    @Mock
    private Specification mockSpec1;

    @Mock
    private Specification mockSpec2;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.request = new Object();

        ArrayList<Specification> specifications = new ArrayList<Specification>();
        specifications.add(this.mockSpec1);
        specifications.add(this.mockSpec2);
        this.compositeSpecification = new AllSatisfiedCompositePropertySpecification(specifications);
    }

    @Test(expected = IllegalArgumentException.class)
    public void null_specification_collection_throws_exception() {
        new AllSatisfiedCompositePropertySpecification((Iterable<Specification>)null);
    }

    @Test
    public void returns_true_if_all_specifications_are_satisfied() {
        when(mockSpec1.isSatisfiedBy(this.request)).thenReturn(true);
        when(mockSpec2.isSatisfiedBy(this.request)).thenReturn(true);

        boolean isSatisfied = this.compositeSpecification.isSatisfiedBy(this.request);
        assertTrue(isSatisfied);
    }

    @Test
    public void returns_false_if_any_specifications_are_not_satisfied() {
        when(mockSpec1.isSatisfiedBy(this.request)).thenReturn(true);
        when(mockSpec2.isSatisfiedBy(this.request)).thenReturn(false);

        boolean isSatisfied = this.compositeSpecification.isSatisfiedBy(this.request);
        assertFalse(isSatisfied);
    }
}
