package com.flextrade.jfixture.specifications;

import java.util.Arrays;

public class AnySatisfiedCompositePropertySpecification implements Specification {

    private final Iterable<Specification> specifications;

    public AnySatisfiedCompositePropertySpecification(Specification... specifications) {
        this(Arrays.asList(specifications));
    }

    public AnySatisfiedCompositePropertySpecification(Iterable<Specification> specifications) {
        if(specifications == null) throw new IllegalArgumentException("specifications");
        this.specifications = specifications;
    }

    @Override
    public boolean isSatisfiedBy(Object request) {
        for(Specification specification : this.specifications) {
            if(specification.isSatisfiedBy(request)) return true;
        }

        return false;
    }
}
