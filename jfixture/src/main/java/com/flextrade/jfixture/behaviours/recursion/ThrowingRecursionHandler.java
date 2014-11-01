package com.flextrade.jfixture.behaviours.recursion;

import com.flextrade.jfixture.exceptions.ObjectCreationException;

import java.util.Collection;
import java.util.List;

class ThrowingRecursionHandler implements RecursionHandler {
    @Override
    public Object handleRecursiveRequest(Object request, List<Object> recordedRequests) throws ObjectCreationException {

        String errorMessage = String.format(
                "Unable to create an instance of %s because it contains a circular reference. %s",
                recordedRequests.get(0), getRecursiveStack(recordedRequests));

        throw new ObjectCreationException(errorMessage);
    }

    private String getRecursiveStack(Collection<Object> requests) {
        StringBuilder msg = new StringBuilder();
        for (Object request : requests) {
            msg.append(request.toString());
            msg.append(" --> ");
        }

        return msg.toString();
    }
}
