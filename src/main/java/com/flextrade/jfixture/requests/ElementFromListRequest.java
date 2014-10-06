package com.flextrade.jfixture.requests;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
public class ElementFromListRequest {

    @Getter
    private final List<Object> list;

    public ElementFromListRequest(List<Object> list) {
        this.list = Collections.unmodifiableList(list);
    }
}
