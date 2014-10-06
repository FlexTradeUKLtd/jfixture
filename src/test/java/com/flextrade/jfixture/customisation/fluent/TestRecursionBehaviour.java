package com.flextrade.jfixture.customisation.fluent;

import com.flextrade.jfixture.CustomisationContainer;
import com.flextrade.jfixture.FluentCustomisation;
import com.flextrade.jfixture.customisation.OmitSpecimenRecursionCustomisation;
import com.flextrade.jfixture.customisation.ThrowingRecursionCustomisation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

public class TestRecursionBehaviour {

    private RecursionBehaviour behaviour;

    @Mock
    private CustomisationContainer mockContainer;

    @Mock
    private FluentCustomisation mockFluentCustomisation;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.behaviour = new RecursionBehaviour(this.mockContainer, this.mockFluentCustomisation);
    }

    @Test
    public void throw_exception_adds_throwing_recursion_customisation() {
        this.behaviour.throwException();
        verify(this.mockContainer).customise(isA(ThrowingRecursionCustomisation.class));
    }

    @Test
    public void throw_exception_returns_the_fluent_customisation() {
        assertSame(this.mockFluentCustomisation, this.behaviour.throwException());
    }

    @Test
    public void omit_specimen_adds_omit_specimen_recursion_customisation() {
        this.behaviour.omitSpecimen();
        verify(this.mockContainer).customise(isA(OmitSpecimenRecursionCustomisation.class));
    }

    @Test
    public void omit_specimen_returns_the_fluent_customisation() {
        assertSame(this.mockFluentCustomisation, this.behaviour.omitSpecimen());
    }
}
