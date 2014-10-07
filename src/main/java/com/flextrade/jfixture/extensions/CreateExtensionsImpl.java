package com.flextrade.jfixture.extensions;

import com.flextrade.jfixture.BuilderContainer;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.ElementFromListRequest;
import com.flextrade.jfixture.requests.RangeRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateExtensionsImpl implements CreateExtensions {

    private final BuilderContainer builderContainer;

    public CreateExtensionsImpl(BuilderContainer builderContainer) {
        this.builderContainer = builderContainer;
    }

    @Override
    public <T extends Comparable<T>> T inRange(Class<T> clazz, T min, T max) {
        Object result = this.create(new RangeRequest(clazz, min, max));

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
