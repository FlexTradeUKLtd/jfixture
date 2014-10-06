package com.flextrade.jfixture.behaviours.autoproperty;

import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.specifications.AllSatisfiedCompositePropertySpecification;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;

public class TestAutoPropertyBehaviour {

    private AutoPropertyBehaviour behaviour;

    @Mock
    private SpecimenBuilder mockBuilder;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.behaviour = new AutoPropertyBehaviour();
    }

    @Test
    public void transform_returns_an_instance_of_auto_property_builder() {
        SpecimenBuilder builder = this.behaviour.transform(this.mockBuilder);
        assertTrue(builder instanceof AutoPropertyBuilder);
    }

    @Test
    public void passes_AllSatisfiedCompositePropertySpecification_to_the_builder() {
        SpecimenBuilder builder = this.behaviour.transform(this.mockBuilder);
        AutoPropertyBuilder autoPropertyBuilder = (AutoPropertyBuilder) builder;
        assertTrue(autoPropertyBuilder.specification() instanceof AllSatisfiedCompositePropertySpecification);
    }
}
