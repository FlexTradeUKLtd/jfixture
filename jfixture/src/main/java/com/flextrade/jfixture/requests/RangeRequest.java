package com.flextrade.jfixture.requests;

import com.flextrade.jfixture.exceptions.InvalidRequestException;

public class RangeRequest<T extends Comparable<T>> {
    private final Object request;
    private final T min;
    private final T max;

    public RangeRequest(Object request, T min, T max) {
       if(min.compareTo(max) >= 0)
           throw new InvalidRequestException("Minimum value in range must be less than maximum");

        this.request = request;
        this.min = min;
        this.max = max;
    }

    public Object getRequest() {
        return this.request;
    }

    public T getMin() {
        return this.min;
    }

    public T getMax() {
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
