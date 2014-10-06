package com.flextrade.jfixture.requests;

import lombok.Data;

import java.lang.reflect.Type;

@Data
public class KeyValueRequest {
    private final Type keyType;
    private final Type valueType;
}
