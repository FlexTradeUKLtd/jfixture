package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.specifications.Specification;
import com.flextrade.jfixture.utility.SpecimenType;

public class JodaTimeSpecification implements Specification {

    @Override
    public boolean isSatisfiedBy(Object request) {
        if (!(request instanceof SpecimenType)) {
            return false;
        }

        SpecimenType<?> requestClass = (SpecimenType<?>) request;
        String name = requestClass.getRawType().getName();

        // We don't want to set the properties of the ReadWritableXxx types in Joda Time
        return !name.startsWith("org.joda");
    }
}