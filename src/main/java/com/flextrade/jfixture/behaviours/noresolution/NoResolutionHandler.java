package com.flextrade.jfixture.behaviours.noresolution;

import java.util.List;

interface NoResolutionHandler {
    Object handleNoResolution(Object request, List<Object> recordedRequests);
}
