package com.flextrade.jfixture.behaviours.autoproperty;

import com.flextrade.jfixture.BehaviourCommand;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.specifications.Specification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class AutoPropertyBuilder implements SpecimenBuilder {

    private final SpecimenBuilder builder;
    private final BehaviourCommand command;
    private final Specification specification;

    @Override
    public Object create(Object request, SpecimenContext context) {
        Object specimen = this.builder.create(request, context);

         if(this.specification.isSatisfiedBy(request))
             command.execute(request, specimen, context);

        return specimen;
    }

    public Specification specification() {
        return this.specification;
    }
}
