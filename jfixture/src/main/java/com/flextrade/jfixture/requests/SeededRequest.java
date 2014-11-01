package com.flextrade.jfixture.requests;

public class SeededRequest {
    private final Object seed;
    private final Object request;

    public SeededRequest(Object seed, Object request) {
        this.seed = seed;
        this.request = request;
    }

    public Object getSeed() {
        return this.seed;
    }

    public Object getRequest() {
        return this.request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeededRequest that = (SeededRequest) o;

        return request.equals(that.request) && seed.equals(that.seed);
    }

    @Override
    public int hashCode() {
        int result = seed.hashCode();
        result = 31 * result + request.hashCode();
        return result;
    }
}
