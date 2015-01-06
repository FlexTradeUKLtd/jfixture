package com.flextrade.jfixture.behaviours.noresolution;

import java.util.List;

class ThrowingNoResolutionHandler implements NoResolutionHandler {

    @Override
    public Object handleNoResolution(Object request, List<Object> recordedRequests) {
        throw new UnsupportedOperationException(errorMessage(request, recordedRequests));
    }

    private String errorMessage(Object request, List<Object> recordedRequests) {
        StringBuilder msg = new StringBuilder();
        msg.append("JFixture was unable to create an instance from ")
                .append(request)
                .append(", most likely because it has no public constructor, is an abstract or non-public type or has no static factory methods")
                .append("\n");

        for (Object r : recordedRequests) {
            msg.append("\t\t")
                    .append(r.toString())
                    .append(" --> ")
                    .append("\n");
        }

        msg.append("\t\t")
                .append(request)
                .append("\n");
        return msg.toString();
    }
}
