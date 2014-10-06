package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenSupplier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class TestCustomBuilder {

    private CustomBuilder builder;

    @Mock
    private SpecimenSupplier<ArrayList> mockSpecimenBuilder;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.builder = new CustomBuilder<ArrayList>(ArrayList.class, this.mockSpecimenBuilder);
    }

    @Test
    public void exact_class_type_invokes_builder() {
        this.builder.create(ArrayList.class, null);
        verify(this.mockSpecimenBuilder).create();
    }

    @Test
    public void different_class_type_returns_no_specimen() {
        Object result = this.builder.create(String.class, null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void different_class_type_does_not_invoke_builder() {
        this.builder.create(String.class, null);
        verify(this.mockSpecimenBuilder, never()).create();
    }
}
