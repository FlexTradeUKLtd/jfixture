package com.flextrade.jfixture.requests;

import lombok.Data;

@Data
public class RangeRequest {
    private final Object request;
    private final Object min;
    private final Object max;
}
