package com.flextrade.jfixture.behaviours.noresolution;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import lombok.RequiredArgsConstructor;

import java.util.Stack;

@RequiredArgsConstructor
class NoResolutionGuard implements SpecimenBuilder {

    private final SpecimenBuilder builder;
    private final NoResolutionHandler noResolutionHandler;
    private final Stack<Object> monitoredRequests = new Stack<Object>();

    @Override
    public Object create(Object request, SpecimenContext context) {

        this.monitoredRequests.push(request);
        Object specimen = this.builder.create(request, context);
        if (specimen instanceof NoSpecimen) {
            this.monitoredRequests.pop();
            return this.noResolutionHandler.handleNoResolution(request, this.monitoredRequests);
        }

        return specimen;
    }

    public SpecimenBuilder builder() {
        return this.builder;
    }

    public NoResolutionHandler handler() {
        return this.noResolutionHandler;
    }
}
