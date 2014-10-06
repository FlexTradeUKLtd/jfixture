package com.flextrade.jfixture.behaviours.recursion;

import com.flextrade.jfixture.SpecimenBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TestOmitSpecimenRecursionBehaviour {

    private OmitSpecimenRecursionBehaviour behaviour;

    @Mock
    private SpecimenBuilder mockSpecimenBuilder;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.behaviour = new OmitSpecimenRecursionBehaviour();
    }

    @Test(expected = IllegalArgumentException.class)
    public void null_builder_throws_exception() {
        this.behaviour.transform(null);
    }

    @Test
    public void returns_instance_of_recursion_guard() {
        SpecimenBuilder transformed = this.behaviour.transform(this.mockSpecimenBuilder);
        assertTrue(transformed instanceof RecursionGuard);
    }

    @Test
    public void recursion_guard_is_given_the_builder() {
        RecursionGuard guard = (RecursionGuard)this.behaviour.transform(this.mockSpecimenBuilder);
        assertSame(guard.builder(), this.mockSpecimenBuilder);
    }

    @Test
    public void recursion_guard_is_given_omit_specimen_handler() {
        RecursionGuard guard = (RecursionGuard)this.behaviour.transform(this.mockSpecimenBuilder);
        assertTrue(guard.handler() instanceof OmitSpecimenRecursionHandler);
    }
}
