package com.flextrade.jfixture.requests;

import com.flextrade.jfixture.utility.SpecimenType;
import lombok.Data;

import java.lang.reflect.Field;

@Data
public class FieldRequest {
    private final Field field;
    private final SpecimenType containingType;
}
