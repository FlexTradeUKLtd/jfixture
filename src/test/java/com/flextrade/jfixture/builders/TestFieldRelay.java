package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.SeededRequest;
import com.flextrade.jfixture.requests.enrichers.RequestEnricher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestFieldRelay {

    private FieldRelay fieldBuilder;

    @Mock
    private SpecimenContext mockSpecimenContext;

    @Mock
    private RequestEnricher mockRequestCreator;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.fieldBuilder = new FieldRelay(this.mockRequestCreator);
    }

    @Test
    public void non_field_request_returns_no_specimen() {
        Object result = this.fieldBuilder.create(String.class, mockSpecimenContext);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void field_request_provides_seeded_request_with_name_and_type_to_context() throws NoSuchFieldException {
        this.fieldBuilder.create(getIntField(), mockSpecimenContext);
        ArgumentCaptor<SeededRequest> argument = ArgumentCaptor.forClass(SeededRequest.class);
        verify(mockSpecimenContext).resolve(argument.capture());

        SeededRequest request = argument.getValue();
        assertEquals("intField", request.getSeed());
        assertEquals(request.getRequest(), Integer.class);
    }

    @Test
    public void field_request_returns_context_result_of_field_type() throws NoSuchFieldException {
        when(mockSpecimenContext.resolve(any(SeededRequest.class))).thenReturn(1);
        Object result = this.fieldBuilder.create(getIntField(), mockSpecimenContext);
        assertEquals(1, result);
    }

    private Field getIntField() throws NoSuchFieldException {
        return TestClass.class.getField("intField");
    }

    private class TestClass {
        public int intField;
    }
}
