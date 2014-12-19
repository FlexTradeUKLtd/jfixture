package com.flextrade.jfixture.requests;

import java.util.Collections;
import java.util.List;

public class ElementFromListRequest {

    private final List<Object> list;

    public ElementFromListRequest(List<Object> list) {
        this.list = Collections.unmodifiableList(list);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementFromListRequest that = (ElementFromListRequest) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    public List<Object> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        return "ElementFromListRequest{" +
                "list=" + list +
                '}';
    }
}
