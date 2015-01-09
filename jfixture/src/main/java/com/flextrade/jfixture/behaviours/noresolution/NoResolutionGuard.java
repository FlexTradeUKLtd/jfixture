package com.flextrade.jfixture.behaviours.noresolution;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.SeededRequest;
import com.flextrade.jfixture.utility.RequestFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class NoResolutionGuard implements SpecimenBuilder {

    private final SpecimenBuilder builder;
    private final NoResolutionHandler noResolutionHandler;
    private final Stack<Object> monitoredRequests = new Stack<Object>();

    public NoResolutionGuard(SpecimenBuilder builder, NoResolutionHandler noResolutionHandler) {
        this.builder = builder;
        this.noResolutionHandler = noResolutionHandler;
    }

    @Override
    public Object create(Object request, SpecimenContext context) {

        this.monitoredRequests.push(request);
        Object specimen = this.builder.create(request, context);
        if (specimen instanceof NoSpecimen) {
            return handleNoResolutionForRequest(request);
        }
        this.monitoredRequests.pop();

        return specimen;
    }

    public SpecimenBuilder builder() {
        return this.builder;
    }

    public NoResolutionHandler handler() {
        return this.noResolutionHandler;
    }

    private Object handleNoResolutionForRequest(Object request) {
        List<Object> filtered = new ArrayList<Object>();
        RequestFilter filter = RequestFilter.onlyDefault();
        for (Object mr : this.monitoredRequests) {
            if (filter.allow(mr))
                filtered.add(mr);
        }

        return this.noResolutionHandler.handleNoResolution(request, filtered);
    }
}
