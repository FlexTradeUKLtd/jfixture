package com.flextrade.jfixture.requests;

public class MultipleRequest {
    private final Object innerRequest;

    public MultipleRequest(Object innerRequest) {
        this.innerRequest = innerRequest;
    }

    public Object getInnerRequest() {
        return this.innerRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultipleRequest that = (MultipleRequest) o;

        return innerRequest.equals(that.innerRequest);
    }

    @Override
    public int hashCode() {
        return innerRequest.hashCode();
    }

    @Override
    public String toString() {
        return "MultipleRequest{" +
                "innerRequest=" + innerRequest +
                '}';
    }
}
