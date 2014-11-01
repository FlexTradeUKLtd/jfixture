package com.flextrade.jfixture.behaviours.tracing;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Member;

import static org.junit.Assert.assertEquals;

public class TestMemberOnlyResponseStrategy {

    private MemberOnlyResponseStrategy strategy;
    private StringBuilder appendable;
    private Object request;
    private Object response;
    private Member memberRequest;

    @Before
    public void initialise() throws NoSuchFieldException {
        this.strategy = new MemberOnlyResponseStrategy();
        this.appendable = new StringBuilder();
        this.request = "request";
        this.memberRequest = this.getClass().getDeclaredField("request");
        this.response = "response";
    }

    @Test
    public void writing_request_does_nothing() throws IOException {
        this.strategy.writeRequest(this.appendable, this.request);

        assertEquals("", this.appendable.toString());
    }

    @Test
    public void writing_response_if_request_is_member_contains_request_and_response() throws IOException {
        this.strategy.writeCreated(this.appendable, this.memberRequest, this.response);

        assertEquals("request = response\n", this.appendable.toString());
    }

    @Test
    public void writing_response_if_request_is_not_member_does_nothing() throws IOException {
        this.strategy.writeCreated(this.appendable, this.request, this.response);

        assertEquals("", this.appendable.toString());
    }
}
