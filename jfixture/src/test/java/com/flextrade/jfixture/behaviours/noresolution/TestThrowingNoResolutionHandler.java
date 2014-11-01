package com.flextrade.jfixture.behaviours.noresolution;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestThrowingNoResolutionHandler {

    private ThrowingNoResolutionHandler handler;
    private Object request;
    private List<Object> recordedRequests;

    @Before
    public void initialise() {
        this.handler = new ThrowingNoResolutionHandler();
        this.request = new Object();
        this.recordedRequests = new ArrayList<Object>();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throws_unsupported_operation_exception_when_handling_request() {
        this.handler.handleNoResolution(this.request, this.recordedRequests);
    }
}
