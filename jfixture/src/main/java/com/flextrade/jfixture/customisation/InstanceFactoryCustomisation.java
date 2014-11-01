package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.SpecimenSupplier;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Type;

public class InstanceFactoryCustomisation<T> implements Customisation {

    private final SpecimenType instanceType;
    private final SpecimenSupplier<? extends  T> specimenSupplier;

    public InstanceFactoryCustomisation(Type instanceType, SpecimenSupplier<? extends  T> supplier) {
        this.instanceType = SpecimenType.of(instanceType);
        this.specimenSupplier = supplier;
    }

    @Override
    public void customise(JFixture fixture) {
        CustomBuilder<T> builder = new CustomBuilder<T>(this.instanceType, this.specimenSupplier);

        fixture.addBuilderToStartOfPipeline(builder);
        fixture.customise(new OmitAutoPropertyCustomisation(this.instanceType));
    }
}
