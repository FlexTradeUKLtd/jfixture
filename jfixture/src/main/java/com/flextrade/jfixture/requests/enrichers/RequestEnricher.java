package com.flextrade.jfixture.requests.enrichers;

import java.lang.annotation.Annotation;

public interface RequestEnricher {
    Object enrich(Object request, Annotation annotation);
}
