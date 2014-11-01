package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class TestSubTypeRelay {

    private SubTypeRelay builder;

    @Mock
    private SpecimenContext mockContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.builder = new SubTypeRelay(List.class, ArrayList.class);
    }

    @Test
    public void non_class_request_returns_no_specimen() {
        Object result = builder.create("string", mockContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void request_class_does_not_equal_base_class_returns_no_specimen() {
        Object result = builder.create(Integer.class, mockContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void request_class_equals_base_class_passes_sub_class_to_context() {
        builder.create(List.class, mockContext);
        verify(mockContext).resolve(ArrayList.class);
    }
}
