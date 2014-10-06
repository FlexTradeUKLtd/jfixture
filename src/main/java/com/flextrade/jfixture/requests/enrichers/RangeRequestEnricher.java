package com.flextrade.jfixture.requests.enrichers;

import com.flextrade.jfixture.annotations.Range;
import com.flextrade.jfixture.requests.RangeRequest;

import java.lang.annotation.Annotation;

public class RangeRequestEnricher implements RequestEnricher {

    public Object enrich(Object request, Annotation annotation) {
        if(!(annotation instanceof Range)) {
            return null;
        }

        Range rangeAnnotation = (Range)annotation;
        return new RangeRequest(request, rangeAnnotation.min(), rangeAnnotation.max());
    }
}
