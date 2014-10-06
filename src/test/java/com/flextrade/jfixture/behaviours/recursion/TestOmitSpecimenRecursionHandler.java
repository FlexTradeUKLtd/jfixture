package com.flextrade.jfixture.behaviours.recursion;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class TestOmitSpecimenRecursionHandler {

    @Test
    public void null_is_returned_when_request_is_handled() {
        // It needs to return {null}, not {NoSpecimen} because {null} signals
        // that the request has actually been handled and the Fixture won't
        // continue to try to fulfill it - which it won't be able to do
        OmitSpecimenRecursionHandler handler = new OmitSpecimenRecursionHandler();
        Object result = handler.handleRecursiveRequest(null, null);
        assertNull(result);
    }
}
