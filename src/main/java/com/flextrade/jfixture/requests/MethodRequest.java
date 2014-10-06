package com.flextrade.jfixture.requests;

import com.flextrade.jfixture.utility.SpecimenType;
import lombok.Data;

import java.lang.reflect.Method;

@Data
public class MethodRequest {
    private final Method method;
    private final SpecimenType containingType;
}
