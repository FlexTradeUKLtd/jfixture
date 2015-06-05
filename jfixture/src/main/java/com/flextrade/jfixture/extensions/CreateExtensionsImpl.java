package com.flextrade.jfixture.extensions;

import com.flextrade.jfixture.BuilderContainer;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.ElementFromListRequest;
import com.flextrade.jfixture.requests.RangeRequest;
import com.flextrade.jfixture.utility.SpecimenType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateExtensionsImpl implements CreateExtensions {

    private final BuilderContainer builderContainer;

    public CreateExtensionsImpl(BuilderContainer builderContainer) {
        this.builderContainer = builderContainer;
    }

    @Override
    public <T, U extends Comparable<U>> T inRange(Class<T> clazz, U min, U max) {
        Object result = this.create(new RangeRequest<U>(SpecimenType.of(clazz), min, max));

        return (T) result;
    }

    @Override
    public <T> T fromList(T... list) {
        List<Object> objects = new ArrayList<Object>();
        Collections.addAll(objects, list);

        Object result = this.create(new ElementFromListRequest(objects));

        return (T) result;
    }

    private Object create(Object request) {
        SpecimenBuilder builder = builderContainer.getBuilder();
        SpecimenContext context = builderContainer.getContext();

        return builder.create(request, context);
    }
}
