package com.flextrade.jfixture.requests;

import com.flextrade.jfixture.utility.SpecimenType;
import lombok.Data;

import java.lang.reflect.Constructor;

@Data
public class GenericConstructorRequest {
    private final Constructor constructor;
    private final SpecimenType containingType;
}
