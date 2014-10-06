package com.flextrade.jfixture.behaviours.tracing;

import com.flextrade.jfixture.FixtureBehaviour;
import com.flextrade.jfixture.SpecimenBuilder;

public class TracingBehaviour implements FixtureBehaviour {

    private final Appendable traceWriter;
    private final TracingStrategy strategy;

    public TracingBehaviour(TracingStrategy strategy, Appendable traceWriter) {

        if (strategy == null) throw new IllegalArgumentException("strategy must not be null");
        if (traceWriter == null) throw new IllegalArgumentException("traceWriter must not be null");

        this.strategy = strategy;
        this.traceWriter = traceWriter;
    }

    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {

        if (builder == null) {
            throw new IllegalArgumentException("builder");
        }

        return new SpecimenTracer(builder, this.strategy, this.traceWriter);
    }
}
