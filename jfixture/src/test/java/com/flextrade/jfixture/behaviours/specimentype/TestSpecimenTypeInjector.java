package com.flextrade.jfixture.behaviours.specimentype;

import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class TestSpecimenTypeInjector {

    private SpecimenTypeInjector injector;

    @Mock SpecimenBuilder mockBuilder;
    @Mock SpecimenContext mockContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        injector = new SpecimenTypeInjector(mockBuilder);
    }

    @Test
    public void if_request_is_a_specimen_type_it_gets_passed_to_the_inner_builder() {
        SpecimenType type = new SpecimenType<String>() { };
        injector.create(type, mockContext);
        verify(mockBuilder).create(type, mockContext);
    }

    @Test
    public void if_request_is_not_a_specimen_type_it_gets_wrapped_in_one_and_passed_to_the_inner_builder() {
        Class type = String.class;
        ArgumentCaptor<Object> requestCaptor = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<SpecimenContext> contextCaptor = ArgumentCaptor.forClass(SpecimenContext.class);
        injector.create(type, mockContext);

        verify(mockBuilder).create(requestCaptor.capture(), contextCaptor.capture());

        Object request = requestCaptor.getValue();
        assertTrue(request instanceof SpecimenType);
        assertEquals(((SpecimenType) request).getRawType(), type);
    }
}
