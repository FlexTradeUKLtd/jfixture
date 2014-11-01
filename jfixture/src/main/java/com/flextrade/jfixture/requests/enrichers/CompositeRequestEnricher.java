package com.flextrade.jfixture.requests.enrichers;

import java.lang.annotation.Annotation;

public class CompositeRequestEnricher implements RequestEnricher {

    private final RequestEnricher[] enrichers;

    public CompositeRequestEnricher(RequestEnricher... enrichers) {
        this.enrichers = enrichers;
    }

    @Override
    public Object enrich(Object request, Annotation annotation) {
        for( RequestEnricher enricher : this.enrichers) {
            Object newRequest = enricher.enrich(request, annotation);
            if(newRequest != null) {
                return newRequest;
            }
        }

        return null;
    }
}
