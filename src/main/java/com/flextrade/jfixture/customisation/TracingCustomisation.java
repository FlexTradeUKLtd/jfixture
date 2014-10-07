package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.tracing.DebugTracingStrategy;
import com.flextrade.jfixture.behaviours.tracing.TracingBehaviour;

public class TracingCustomisation implements Customisation {

    private final Appendable traceWriter;

    public TracingCustomisation(Appendable traceWriter) {
        this.traceWriter = traceWriter;
    }

    @Override
    public void customise(JFixture fixture) {
        TracingBehaviour behaviour = new TracingBehaviour(new DebugTracingStrategy(), this.traceWriter);
        fixture.behaviours().add(behaviour);
    }
}
