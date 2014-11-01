package com.flextrade.jfixture.behaviours.autoproperty;

import com.flextrade.jfixture.BehaviourCommand;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.specifications.Specification;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestAutoPropertyBuilder {

    private AutoPropertyBuilder autoPropertyBuilder;

    @Mock
    private SpecimenBuilder mockBuilder;

    @Mock
    private BehaviourCommand mockCommand;

    @Mock
    private Specification mockSpecification;

    @Mock
    private SpecimenContext mockContext;

    private final Object request = new Object();
    private final Object expectedSpecimen = new Object();

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        autoPropertyBuilder = new AutoPropertyBuilder(
                this.mockBuilder,
                this.mockCommand,
                this.mockSpecification);
    }

    @Test
    public void create_method_returns_the_specimen_creator_by_the_builder() {
        when(this.mockBuilder.create(request, mockContext)).thenReturn(expectedSpecimen);
        Object createdSpecimen = this.autoPropertyBuilder.create(this.request, this.mockContext);
        assertSame(expectedSpecimen, createdSpecimen);
    }

    @Test
    public void calls_execute_on_command_if_specification_is_satisfied() {
        when(this.mockBuilder.create(request, mockContext)).thenReturn(expectedSpecimen);
        when(this.mockSpecification.isSatisfiedBy(request)).thenReturn(true);
        this.autoPropertyBuilder.create(this.request, this.mockContext);
        verify(this.mockCommand).execute(this.request, this.expectedSpecimen, this.mockContext);
    }

    @Test
    public void does_not_call_execute_on_command_if_specification_is_noy_satisfied() {
        when(this.mockBuilder.create(request, mockContext)).thenReturn(expectedSpecimen);
        when(this.mockSpecification.isSatisfiedBy(request)).thenReturn(false);
        this.autoPropertyBuilder.create(this.request, this.mockContext);
        verify(this.mockCommand, never()).execute(this.request, this.expectedSpecimen, this.mockContext);
    }
}
