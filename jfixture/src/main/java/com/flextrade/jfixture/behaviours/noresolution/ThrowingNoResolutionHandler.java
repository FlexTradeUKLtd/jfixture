package com.flextrade.jfixture.behaviours.noresolution;

import java.util.List;

class ThrowingNoResolutionHandler implements NoResolutionHandler {

    @Override
    public Object handleNoResolution(Object request, List<Object> recordedRequests) {
        throw new UnsupportedOperationException(errorMessage(request, recordedRequests));
    }

    private String errorMessage(Object request, List<Object> recordedRequests) {
        StringBuilder msg = new StringBuilder();
        msg.append("JFixture was unable to create an instance of ")
                .append(request)  .append("\n")
                .append("Most likely because it has no public constructor, is an abstract or non-public type or has no static factory methods.").append("\n")
                .append("If this isn't the case it's likely that all constructors and factory methods on the type have thrown an exception.").append("\n")
                .append("To view any thrown exceptions just add the Tracing Customisation to the JFixture instance, e.g. fixture.customise(new TracingCustomisation(System.out));").append("\n")
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
