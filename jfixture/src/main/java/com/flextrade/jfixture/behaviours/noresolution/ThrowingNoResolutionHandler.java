package com.flextrade.jfixture.behaviours.noresolution;

import com.flextrade.jfixture.utility.StringUtil;

import java.util.List;

class ThrowingNoResolutionHandler implements NoResolutionHandler {

    private final FailedRequestEstimator failedRequestEstimator = new FailedRequestEstimator();

    @Override
    public Object handleNoResolution(Object request, List<Object> recordedRequests) {
        throw new UnsupportedOperationException(errorMessage(request, recordedRequests));
    }

    private String errorMessage(Object request, List<Object> recordedRequests) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unable to fulfill request");
        stringBuilder.append("\n");

        int depth = 0;
        for (Object requestInList : recordedRequests) {
            stringBuilder
                    .append(StringUtil.repeat("\t", depth++))
                    .append("Request for ").append(requestInList.toString())
                    .append("\n");
        }

        stringBuilder
                .append(StringUtil.repeat("\t", depth - 1))
                .append(this.failedRequestEstimator.getEstimatedFailureReason(request));

        return stringBuilder.toString();
    }
}
