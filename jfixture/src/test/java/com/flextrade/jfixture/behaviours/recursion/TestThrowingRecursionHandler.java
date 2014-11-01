package com.flextrade.jfixture.behaviours.recursion;

import com.flextrade.jfixture.exceptions.ObjectCreationException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestThrowingRecursionHandler {

    private ThrowingRecursionHandler handler;
    private Object request;
    private List<Object> recordedRequests;

    @Before
    public void initialise() {
        this.handler = new ThrowingRecursionHandler();
        this.request = new Object();
        this.recordedRequests = new ArrayList<Object>();
        this.recordedRequests.add(this.request);
    }

    @Test(expected = ObjectCreationException.class)
    public void throws_object_creation_exception_when_handling_request() {
        this.handler.handleRecursiveRequest(this.request, this.recordedRequests);
    }
}
