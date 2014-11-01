package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.KeyValueRequest;
import com.flextrade.jfixture.requests.MultipleRequest;
import com.flextrade.jfixture.utility.KeyValuePair;
import com.flextrade.jfixture.utility.SpecimenType;

import java.util.List;
import java.util.Map;

class MapRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType specimenType = (SpecimenType)request;
        Class mapClass = specimenType.getRawType();
        if (!Map.class.isAssignableFrom(mapClass)) {
            return new NoSpecimen();
        }

        if(specimenType.getGenericTypeArguments().getLength() != 2) {
            return new NoSpecimen();
        }

        Object resolved = getKeyValuePairs(context, specimenType);

        if (!(resolved instanceof List)) {
            return new NoSpecimen();
        }

        return createMap(context, mapClass, (List<KeyValuePair>) resolved);
    }

    private Map createMap(SpecimenContext context, Class mapClass, List<KeyValuePair> resolved) {
        Map mapInstance = (Map) context.resolve(mapClass);
        for (KeyValuePair entry : resolved) {
            mapInstance.put(entry.getKey(), entry.getValue());
        }
        return mapInstance;
    }

    private Object getKeyValuePairs(SpecimenContext context, SpecimenType specimenType) {
        SpecimenType keyType = specimenType.getGenericTypeArguments().get(0).getType();
        SpecimenType valueType = specimenType.getGenericTypeArguments().get(1).getType();


        MultipleRequest multipleRequest = createMultipleRequest(keyType, valueType);
        return context.resolve(multipleRequest);
    }

    private MultipleRequest createMultipleRequest(SpecimenType keyType, SpecimenType valueType) {
        return new MultipleRequest(new KeyValueRequest(keyType, valueType));
    }
}
