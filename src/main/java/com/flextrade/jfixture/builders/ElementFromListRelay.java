package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.ElementFromListRequest;
import com.flextrade.jfixture.utility.ElementFromListStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ElementFromListRelay implements SpecimenBuilder {

    private final ElementFromListStrategy strategy;

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!(request instanceof ElementFromListRequest)) {
            return new NoSpecimen();
        }

        ElementFromListRequest listRequest = (ElementFromListRequest) request;

        if (listRequest.getList().isEmpty()) {
            throw new ObjectCreationException("List of elements was empty");
        }

        return this.strategy.get(listRequest.getList());
    }
}
