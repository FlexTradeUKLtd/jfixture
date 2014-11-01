package com.flextrade.jfixture.behaviours.tracing;

import com.flextrade.jfixture.exceptions.ObjectCreationException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestDebugTracingStrategy {

    private DebugTracingStrategy strategy;
    private StringBuilder appendable;
    private Object request;
    private Object response;

    @Before
    public void initialise() {
        this.strategy = new DebugTracingStrategy();
        this.appendable = new StringBuilder();
        this.request = "request";
        this.response = "response";
    }

    @Test
    public void writing_request_is_formatted_correctly() throws IOException {
        this.strategy.writeRequest(this.appendable, this.request);

        assertEquals("Requested: request\n", this.appendable.toString());
    }

    @Test
    public void writing_multiple_requests_are_indented() throws IOException {
        this.strategy.writeRequest(this.appendable, this.request);
        this.strategy.writeRequest(this.appendable, this.request);

        assertEquals("Requested: request\n\tRequested: request\n", this.appendable.toString());
    }

    @Test
    public void writing_response_is_formatted_correctly() throws IOException {
        this.strategy.writeCreated(this.appendable, this.request, this.response);

        assertEquals("Created: response\n", this.appendable.toString());
    }

    @Test
    public void writing_multiple_requests_and_responses_are_indented() throws IOException {
        this.strategy.writeRequest(this.appendable, this.request);
        this.strategy.writeRequest(this.appendable, this.request);
        this.strategy.writeCreated(this.appendable, this.request, this.response);
        this.strategy.writeCreated(this.appendable, this.request, this.response);

        assertEquals("Requested: request\n\tRequested: request\n\tCreated: response\nCreated: response\n", this.appendable.toString());
    }

    @Test
    public void writing_errors_includes_the_inner_exceptions() throws IOException {
        String nullPointerMessage = "null pointer message";
        String objectCreationMessage = "creation message";
        NullPointerException nullPointerException = new NullPointerException(nullPointerMessage);
        Exception exception = new ObjectCreationException(objectCreationMessage, nullPointerException);

        this.strategy.writeError(this.appendable, exception);

        String errorMessage = this.appendable.toString();

        assertTrue(errorMessage.contains(nullPointerMessage));
        assertTrue(errorMessage.contains(objectCreationMessage));
    }
}
