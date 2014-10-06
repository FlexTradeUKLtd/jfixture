package com.flextrade.jfixture.customisation.fluent;

import com.flextrade.jfixture.CustomisationContainer;
import com.flextrade.jfixture.FluentCustomisation;
import com.flextrade.jfixture.customisation.AutoPropertyCustomisation;
import com.flextrade.jfixture.customisation.OmitAutoPropertyCustomisation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

public class TestAutoPropertyBehaviour {

    private AutoPropertyBehaviour behaviour;

    @Mock
    private CustomisationContainer mockContainer;

    @Mock
    private FluentCustomisation mockFluentCustomisation;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.behaviour = new AutoPropertyBehaviour (this.mockContainer, this.mockFluentCustomisation);
    }

    @Test
    public void omit_specimen_adds_omit_auto_property_customisation() {
        this.behaviour.omitSpecimen();
        verify(this.mockContainer).customise(isA(OmitAutoPropertyCustomisation.class));
    }

    @Test
    public void omit_specimen_returns_the_fluent_customisation() {
        assertSame(this.mockFluentCustomisation, this.behaviour.omitSpecimen());
    }

    @Test
    public void populate_specimen_adds_auto_property_customisation() {
        this.behaviour.populateSpecimen();
        verify(this.mockContainer).customise(isA(AutoPropertyCustomisation.class));
    }

    @Test
    public void populate_specimen_returns_the_fluent_customisation() {
        assertSame(this.mockFluentCustomisation, this.behaviour.populateSpecimen());
    }
}
