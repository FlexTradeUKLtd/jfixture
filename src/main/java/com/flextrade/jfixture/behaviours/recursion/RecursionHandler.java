package com.flextrade.jfixture.behaviours.recursion;

import java.util.List;

interface RecursionHandler {
    Object handleRecursiveRequest(Object request, List<Object> recordedRequests);
}
