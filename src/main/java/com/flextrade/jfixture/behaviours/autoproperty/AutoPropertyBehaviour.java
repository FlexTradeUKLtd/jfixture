package com.flextrade.jfixture.behaviours.autoproperty;

import com.flextrade.jfixture.FixtureBehaviour;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.specifications.AllSatisfiedCompositePropertySpecification;
import com.flextrade.jfixture.specifications.Specification;

import java.util.ArrayList;
import java.util.Collection;

public class AutoPropertyBehaviour implements FixtureBehaviour {

    private final Collection<Specification> allSpecifications;

    public AutoPropertyBehaviour() {
        this.allSpecifications = new ArrayList<Specification>();
        this.allSpecifications.add(new DefaultAutoPropertySpecification());
    }

    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {
        return new AutoPropertyBuilder(
                builder,
                new AutoPropertyPopulater(),
                new AllSatisfiedCompositePropertySpecification(this.allSpecifications));
    }

    public Collection<Specification> specifications() {
        return this.allSpecifications;
    }
}
