package com.flextrade.jfixture.utility;

import com.flextrade.jfixture.requests.MultipleRequest;
import com.flextrade.jfixture.requests.SeededRequest;

public abstract class RequestFilter {
    public abstract boolean allow(Object request);

    public static RequestFilter onlyDefault() {
        return new DefaultRequestFilter();
    }

    public static RequestFilter all() {
        return new AllowAllRequestFilter();
    }

    static class DefaultRequestFilter extends RequestFilter {
        @Override
        public boolean allow(Object request) {
            return !(request instanceof SeededRequest) && !(request instanceof MultipleRequest);
        }
    }
    static class AllowAllRequestFilter extends RequestFilter {
        @Override
        public boolean allow(Object request) {
            return true;
        }
    }
}
