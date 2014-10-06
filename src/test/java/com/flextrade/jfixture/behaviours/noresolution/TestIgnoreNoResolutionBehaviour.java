package com.flextrade.jfixture.behaviours.noresolution;

import com.flextrade.jfixture.SpecimenBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TestIgnoreNoResolutionBehaviour {

    private IgnoreNoResolutionBehaviour behaviour;

    @Mock
    private SpecimenBuilder mockSpecimenBuilder;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.behaviour = new  IgnoreNoResolutionBehaviour();
    }

    @Test(expected = IllegalArgumentException.class)
    public void null_builder_throws_exception() {
        this.behaviour.transform(null);
    }

    @Test
    public void returns_instance_of_no_resolution_guard() {
        SpecimenBuilder transformed = this.behaviour.transform(this.mockSpecimenBuilder);
        assertTrue(transformed instanceof NoResolutionGuard);
    }

    @Test
    public void no_resolution_guard_is_given_the_builder() {
        NoResolutionGuard guard = (NoResolutionGuard)this.behaviour.transform(this.mockSpecimenBuilder);
        assertSame(guard.builder(), this.mockSpecimenBuilder);
    }

    @Test
    public void no_resolution_guard_is_given_ignore_handler() {
        NoResolutionGuard guard = (NoResolutionGuard)this.behaviour.transform(this.mockSpecimenBuilder);
        assertTrue(guard.handler() instanceof IgnoreNoResolutionHandler);
    }
}
