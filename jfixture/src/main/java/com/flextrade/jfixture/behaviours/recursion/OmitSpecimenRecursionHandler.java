package com.flextrade.jfixture.behaviours.recursion;

import java.util.List;

class OmitSpecimenRecursionHandler implements RecursionHandler {
    @Override
    public Object handleRecursiveRequest(Object request, List<Object> recordedRequests) {
        // This needs to return {null}, not {NoSpecimen} because {null} signals
        // that the request has actually been handled and the Fixture won't
        // continue to try to fulfill it - which it won't be able to
        return null;
    }
}
