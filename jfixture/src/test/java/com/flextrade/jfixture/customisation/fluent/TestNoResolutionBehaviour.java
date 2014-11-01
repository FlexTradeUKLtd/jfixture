package com.flextrade.jfixture.customisation.fluent;

import com.flextrade.jfixture.CustomisationContainer;
import com.flextrade.jfixture.FluentCustomisation;
import com.flextrade.jfixture.customisation.IgnoreNoResolutionCustomisation;
import com.flextrade.jfixture.customisation.ThrowOnNoResolutionCustomisation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.verify;

public class TestNoResolutionBehaviour {

    private NoResolutionBehaviour behaviour;

    @Mock
    private CustomisationContainer mockContainer;

    @Mock
    private FluentCustomisation mockFluentCustomisation;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.behaviour = new NoResolutionBehaviour(this.mockContainer, this.mockFluentCustomisation);
    }

    @Test
    public void throw_exception_adds_throw_on_no_resolution_customisation() {
        this.behaviour.throwException();
        verify(this.mockContainer).customise(isA(ThrowOnNoResolutionCustomisation.class));
    }

    @Test
    public void throw_exception_returns_the_fluent_customisation() {
        assertSame(this.mockFluentCustomisation, this.behaviour.throwException());
    }

    @Test
    public void omit_specimen_adds_ignore_no_resolution_customisation() {
        this.behaviour.omitSpecimen();
        verify(this.mockContainer).customise(isA(IgnoreNoResolutionCustomisation.class));
    }

    @Test
    public void omit_specimen_returns_the_fluent_customisation() {
        assertSame(this.mockFluentCustomisation, this.behaviour.omitSpecimen());
    }
}
