package com.flextrade.jfixture.behaviours.tracing;

import com.flextrade.jfixture.SpecimenBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TestTracingBehaviour {

    private TracingBehaviour behaviour;

    @Mock
    private Appendable mockAppendable;

    @Mock
    private SpecimenBuilder mockSpecimenBuilder;

    @Mock
    private TracingStrategy mockStrategy;

    @Before
    public void initialise() {
        MockitoAnnotations.initMocks(this);
        this.behaviour = new TracingBehaviour(this.mockStrategy, this.mockAppendable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void null_appendable_throws_exception() {
        this.behaviour = new TracingBehaviour(this.mockStrategy, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void null_strategy_throws_exception() {
        this.behaviour = new TracingBehaviour(null, this.mockAppendable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void null_builder_throws_exception() {
        this.behaviour.transform(null);
    }

    @Test
    public void transform_returns_specimen_tracer() {
        SpecimenBuilder transformed = this.behaviour.transform(this.mockSpecimenBuilder);
        assertTrue(transformed instanceof SpecimenTracer);
    }

    @Test
    public void transform_returns_specimen_tracer_with_correct_appendable() {
        SpecimenTracer tracer = (SpecimenTracer) this.behaviour.transform(this.mockSpecimenBuilder);
        assertSame(this.mockAppendable, tracer.getTraceWriter());
    }
}
