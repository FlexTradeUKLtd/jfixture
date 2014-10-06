package com.flextrade.jfixture.requests;

import lombok.Data;

@Data
public class SeededRequest {
    private final Object seed;
    private final Object request;
}
