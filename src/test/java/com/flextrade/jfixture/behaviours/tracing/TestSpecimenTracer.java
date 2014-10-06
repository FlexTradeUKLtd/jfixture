package com.flextrade.jfixture.behaviours.tracing;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestSpecimenTracer {

    private SpecimenTracer tracer;

    @Mock
    private SpecimenBuilder mockBuilder;

    @Mock
    private TracingStrategy mockStrategy;

    @Mock
    private Appendable mockAppendable;

    @Mock
    private SpecimenContext mockContext;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.tracer = new SpecimenTracer(this.mockBuilder, this.mockStrategy, this.mockAppendable);
    }

    @Test
    public void tells_strategy_to_write_the_request() throws IOException {
        Object request = new Object();
        this.tracer.create(request, this.mockContext);
        verify(mockStrategy).writeRequest(this.mockAppendable, request);
    }

    @Test
    public void tells_strategy_to_write_response_if_not_no_specimen() throws IOException {
        Object request = new Object();
        Object response = new Object();
        when(this.mockBuilder.create(request, this.mockContext)).thenReturn(response);

        this.tracer.create(request, this.mockContext);

        verify(this.mockStrategy).writeCreated(this.mockAppendable, request, response);
    }

    @Test
    public void doest_not_tell_strategy_to_write_response_if_no_specimen() throws IOException {
        Object request = new Object();
        Object response = new NoSpecimen();
        when(this.mockBuilder.create(request, this.mockContext)).thenReturn(response);

        this.tracer.create(request, this.mockContext);

        verify(this.mockStrategy, never()).writeCreated(this.mockAppendable, request, response);
    }
}
