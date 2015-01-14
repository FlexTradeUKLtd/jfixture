package com.flextrade.jfixture;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestSpecimenBuilderContext {

    private SpecimenBuilderContext context;

    @Mock
    private SpecimenBuilder mockSpecimenBuilder;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.context = new SpecimenBuilderContext(mockSpecimenBuilder);
    }

    @Test
    public void resolve_calls_builder_with_request_and_itself_as_the_context() {
        this.context.resolve(String.class);
        verify(mockSpecimenBuilder).create(String.class, this.context);
    }

    @Test
    public void resolve_returns_result_on_builder() {
        Object builderResult = new Object();
        when(mockSpecimenBuilder.create(String.class, this.context)).thenReturn(builderResult);
        Object result = this.context.resolve(String.class);
        assertSame(builderResult, result);
    }
}
