package com.flextrade.jfixture.behaviours.noresolution;

import com.flextrade.jfixture.NoSpecimen;

import java.util.List;

class IgnoreNoResolutionHandler implements NoResolutionHandler {
    @Override
    public Object handleNoResolution(Object request, List<Object> recordedRequests) {
        return new NoSpecimen();
    }
}
