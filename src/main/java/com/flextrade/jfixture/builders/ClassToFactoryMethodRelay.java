package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.FactoryMethodQuery;
import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.FactoryMethodRequest;
import com.flextrade.jfixture.specifications.Specification;
import com.flextrade.jfixture.utility.SpecimenType;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.util.List;

@RequiredArgsConstructor
class ClassToFactoryMethodRelay implements SpecimenBuilder {

    private final FactoryMethodQuery factoryMethodQuery;
    private final Specification specification;

    @Override
    public Object create(Object request, SpecimenContext context) {
        if (!this.specification.isSatisfiedBy(request)) {
            return new NoSpecimen();
        }

        if(!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        SpecimenType specimenType = (SpecimenType)request;
        List<Method> factoryMethods = this.factoryMethodQuery.getFactoryMethodsForType(specimenType);
        if (factoryMethods.isEmpty()) {
            return new NoSpecimen();
        }

        for (Method method : factoryMethods) {
            try {
                Object specimen = context.resolve(new FactoryMethodRequest(method, specimenType));
                if (!(specimen instanceof NoSpecimen)) {
                    return specimen;
                }
            } catch (ObjectCreationException e) {
                // Factory method might fail so try the next one
                // This exception will show up if tracing is enabled
            }
        }

        return new NoSpecimen();
    }
}
