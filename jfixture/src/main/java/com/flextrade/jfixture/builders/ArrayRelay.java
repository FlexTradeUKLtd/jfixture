package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.MultipleRequest;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Array;
import java.util.List;

class ArrayRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType specimenType = (SpecimenType)request;
        if(!specimenType.getRawType().isArray()) {
            return new NoSpecimen();
        }

        Class clazz = specimenType.getRawType();

        return buildArray(clazz, context);
    }

    private Object buildArray(Class clazz, SpecimenContext context)  {
        Class componentType = clazz.getComponentType();

        return createArrayWithContentsOfType(context, componentType);
    }

    private Object createArrayWithContentsOfType(SpecimenContext context, Class componentType) {
        List contents = (List)context.resolve(new MultipleRequest(componentType));
        Object array = Array.newInstance(componentType, contents.size());

        for(int i = 0; i < contents.size(); i++) {
            Array.set(array, i, contents.get(i));
        }

        return array;
    }
}
