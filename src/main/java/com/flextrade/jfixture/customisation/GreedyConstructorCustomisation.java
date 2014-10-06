package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.builders.ClassToConstructorRelay;
import com.flextrade.jfixture.ConstructorQuery;
import com.flextrade.jfixture.DefaultConstructorQuery;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.specifications.SpecificTypeSpecification;
import com.flextrade.jfixture.utility.SpecimenType;
import com.flextrade.jfixture.utility.comparators.ConstructorParameterCountComparator;
import com.flextrade.jfixture.utility.comparators.InverseComparator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public class GreedyConstructorCustomisation implements Customisation {

    private final SpecimenType type;
    private final ConstructorQuery mostParameterCountConstructorQuery;

    public GreedyConstructorCustomisation(Type type) {
        this.type = SpecimenType.of(type);
        this.mostParameterCountConstructorQuery = new DefaultConstructorQuery(new InverseComparator<Constructor<?>>(new ConstructorParameterCountComparator()));
    }

    @Override
    public void customise(JFixture fixture) {
        SpecimenBuilder greedyConstructorRelay = new ClassToConstructorRelay(mostParameterCountConstructorQuery, new SpecificTypeSpecification(this.type));
        fixture.addBuilderToStartOfPipeline(greedyConstructorRelay);
    }
}
