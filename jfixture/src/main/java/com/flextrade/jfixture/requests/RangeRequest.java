package com.flextrade.jfixture.requests;

public class RangeRequest {
    private final Object request;
    private final Object min;
    private final Object max;

    public RangeRequest(Object request, Object min, Object max) {
        this.request = request;
        this.min = min;
        this.max = max;
    }

    public Object getRequest() {
        return this.request;
    }

    public Object getMin() {
        return this.min;
    }

    public Object getMax() {
        return this.max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RangeRequest that = (RangeRequest) o;

        return max.equals(that.max) && min.equals(that.min) && request.equals(that.request);
    }

    @Override
    public int hashCode() {
        int result = request.hashCode();
        result = 31 * result + min.hashCode();
        result = 31 * result + max.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RangeRequest{" +
                "request=" + request +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
