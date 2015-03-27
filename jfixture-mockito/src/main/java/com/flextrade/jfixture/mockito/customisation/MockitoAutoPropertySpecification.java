package com.flextrade.jfixture.mockito.customisation;

import com.flextrade.jfixture.specifications.Specification;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Modifier;

class MockitoAutoPropertySpecification implements Specification {

    @Override
    public boolean isSatisfiedBy(Object request) {

        if(!(request instanceof SpecimenType)) {
            return false;
        }

        // No need to auto populate the properties of
        // an interface/abstract class because they'll be mocked
        SpecimenType<?> requestClass = (SpecimenType<?>)request;
        if(requestClass.getRawType().isInterface() || Modifier.isAbstract(requestClass.getRawType().getModifiers())) {
            return false;
        }

        String requestName = requestClass.getRawType().getName();
        Boolean isAMock = requestName.contains("Mockito");
        return !isAMock;
    }
}
