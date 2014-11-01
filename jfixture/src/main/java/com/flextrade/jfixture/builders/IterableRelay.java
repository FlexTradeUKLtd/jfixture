package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.requests.MultipleRequest;
import com.flextrade.jfixture.utility.SpecimenType;

import java.util.Collection;

class IterableRelay implements SpecimenBuilder {

    @SuppressWarnings("unchecked")
    @Override
    public Object create(final Object request, SpecimenContext context) {
        if(!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType specimenType = (SpecimenType)request;
        Class iterableClass = specimenType.getRawType();

        if (!typeIsIterable(iterableClass) || !typeHasOneGenericArgument(specimenType)) {
            return new NoSpecimen();
        }

        Object specimen = context.resolve(iterableClass);
        if(!(specimen instanceof Collection)) {
            return new NoSpecimen();
        }

        Collection collection = (Collection) specimen;
        SpecimenType genericType = collectionGenericType(specimenType);
        Object resolved = context.resolve(new MultipleRequest(genericType));
        if (!(resolved instanceof Collection))
            return new NoSpecimen();

        Collection collectionElements = (Collection) resolved;
        collection.addAll(collectionElements);

        return collection;
    }

    private SpecimenType collectionGenericType(SpecimenType specimenType) {
        return specimenType.getGenericTypeArguments().get(0).getType();
    }

    private boolean typeHasOneGenericArgument(SpecimenType specimenType) {
        return specimenType.getGenericTypeArguments().getLength() == 1;
    }

    private boolean typeIsIterable(Class iterableClass) {
        return Iterable.class.isAssignableFrom(iterableClass);
    }
}
