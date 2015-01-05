package com.flextrade.jfixture.behaviours.recursion;

import com.flextrade.jfixture.exceptions.ObjectCreationException;

import java.util.Collection;
import java.util.List;

class ThrowingRecursionHandler implements RecursionHandler {
    @Override
    public Object handleRecursiveRequest(Object request, List<Object> recordedRequests) {

        String errorMessage = String.format(
                "Unable to create an instance of %s because it contains a circular reference.\n%s",
                recordedRequests.get(0), getRecursiveStack(request, recordedRequests));

        throw new ObjectCreationException(errorMessage);
    }

    private String getRecursiveStack(Object request, Collection<Object> requests) {
        StringBuilder msg = new StringBuilder();
        for (Object r : requests) {
            msg.append("\t\t");
            msg.append(r.toString());
            msg.append(" --> ");
            msg.append("\n");
        }

        msg.append("\t\t");
        msg.append(request);
        msg.append("\n");
        return msg.toString();
    }
}
