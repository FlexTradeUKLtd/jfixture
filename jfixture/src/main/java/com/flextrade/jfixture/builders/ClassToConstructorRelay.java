package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.ConstructorQuery;
import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.GenericConstructorRequest;
import com.flextrade.jfixture.specifications.Specification;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;

public class ClassToConstructorRelay implements SpecimenBuilder {

    private final ConstructorQuery constructorQuery;
    private final Specification specification;

    public ClassToConstructorRelay(ConstructorQuery constructorQuery, Specification specification) {
        this.constructorQuery = constructorQuery;
        this.specification = specification;
    }

    @Override
    public Object create(final Object request, SpecimenContext context) {
        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }

        if (!this.specification.isSatisfiedBy(request)) {
            return new NoSpecimen();
        }

        SpecimenType specimenType =  (SpecimenType)request;

        Class rawTypeClass = specimenType.getRawType();
        if (rawTypeClass.isInterface() || Modifier.isAbstract(rawTypeClass.getModifiers())) {
            return new NoSpecimen();
        }

        List<Constructor<?>> constructors = constructorQuery.getConstructorsForClass(rawTypeClass);
        if (constructors == null || constructors.isEmpty()) {
            return new NoSpecimen();
        }
        for (Constructor constructor : constructors) {
            try {
                GenericConstructorRequest constructorRequest = new GenericConstructorRequest(constructor, specimenType);

                Object specimen = context.resolve(constructorRequest);

                if (!(specimen instanceof NoSpecimen)) {
                    return specimen;
                }
            } catch (ObjectCreationException e) {
                // Constructor might fail so try the next one
                // This exception will show up if tracing is enabled
            }
        }

        return new NoSpecimen();
    }
}
