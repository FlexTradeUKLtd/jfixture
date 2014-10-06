package com.flextrade.jfixture.behaviours.tracing;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;

import java.io.IOException;

class SpecimenTracer implements SpecimenBuilder {

    private final TracingStrategy tracingStrategy;
    private final Appendable traceWriter;
    private final SpecimenBuilder builder;

    public SpecimenTracer(SpecimenBuilder builder,
                          TracingStrategy tracingStrategy,
                          Appendable traceWriter) {
        this.tracingStrategy = tracingStrategy;
        this.traceWriter = traceWriter;
        this.builder = builder;
    }

    @Override
    public Object create(Object request, SpecimenContext context) {
        return createAndTraceSpecimen(request, context);
    }

    private Object createAndTraceSpecimen(Object request, SpecimenContext context) {
        Object specimen;
        try {
            this.tracingStrategy.writeRequest(this.traceWriter, request);

            specimen = createSpecimen(request, context);

            if (!(specimen instanceof NoSpecimen)) {
                this.tracingStrategy.writeCreated(this.traceWriter, request, specimen);
            }
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }
        return specimen;
    }

    public Appendable getTraceWriter() {
        return this.traceWriter;
    }

    public TracingStrategy getTracingStrategy() {
        return this.tracingStrategy;
    }

    private Object createSpecimen(Object request, SpecimenContext context) throws IOException {
        Object specimen;
        try {
            specimen = this.builder.create(request, context);
        } catch (ObjectCreationException e) {
            this.tracingStrategy.writeError(this.traceWriter, e);
            throw e;
        }
        return specimen;
    }
}
