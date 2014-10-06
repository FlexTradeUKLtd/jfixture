package com.flextrade.jfixture;

import com.flextrade.jfixture.FinalBuilderContainer;
import com.flextrade.jfixture.FixtureBehaviour;
import com.flextrade.jfixture.FixtureBehaviours;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.builders.CompositeBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TestFixtureBehaviours {

    private FixtureBehaviours behaviours;

    @Mock
    private FinalBuilderContainer mockBuilderContainer;

    @Mock
    private SpecimenBuilder mockSpecimenBuilder;

    @Mock
    private FixtureBehaviour mockBehaviour;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.behaviours = new FixtureBehaviours(mockBuilderContainer, mockSpecimenBuilder);
    }

    @Test
    public void adding_behaviour_sets_the_builder_on_the_container() {
        this.behaviours.add(this.mockBehaviour);
        verify(this.mockBuilderContainer).setFinalBuilder(any(CompositeBuilder.class));
    }

    @Test
    public void removing_behaviour_sets_the_builder_on_the_container() {
        this.behaviours.add(this.mockBehaviour);
        this.behaviours.remove(this.mockBehaviour.getClass());
        verify(this.mockBuilderContainer, times(2)).setFinalBuilder(any(SpecimenBuilder.class));
    }

    @Test
    public void removing_behaviour_does_not_set_builder_if_behaviour_not_found() {
        this.behaviours.remove(this.mockBehaviour.getClass());
        verify(this.mockBuilderContainer, never()).setFinalBuilder(any(SpecimenBuilder.class));
    }

    @Test
    public void removing_behaviour_returns_true_if_behaviour_found() {
        this.behaviours.add(this.mockBehaviour);
        assertTrue(this.behaviours.remove(this.mockBehaviour.getClass()));
    }

    @Test
    public void removing_behaviour_returns_false_if_behaviour_not_found() {
        assertFalse(this.behaviours.remove(this.mockBehaviour.getClass()));
    }

    @Test
    public void finding_behaviour_returns_added_behaviour_of_that_type() {
        this.behaviours.add(this.mockBehaviour);
        FixtureBehaviour found = this.behaviours.find(this.mockBehaviour.getClass());
        assertSame(this.mockBehaviour, found);
    }

    @Test
    public void finding_behaviour_returns_null_if_behaviour_type_not_added() {
        FixtureBehaviour found = this.behaviours.find(this.mockBehaviour.getClass());
        assertNull(found);
    }
}
