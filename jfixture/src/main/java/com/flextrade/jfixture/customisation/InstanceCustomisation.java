package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.SpecimenSupplier;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Type;

public class InstanceCustomisation<T> implements Customisation {

    private final Object instance;
    private final SpecimenType instanceType;

    public InstanceCustomisation(Class<T> clazz, T instance) {
        this.instance = instance;
        this.instanceType = SpecimenType.of(clazz);
    }

    public InstanceCustomisation(Type type, Object instance) {
        this.instance = instance;
        this.instanceType = SpecimenType.of(type);
    }

    @Override
    public void customise(JFixture fixture) {
        SpecimenSupplier<? extends T> supplier = new SpecimenSupplier<T>() {
            @Override
            public T create() {
                return (T)instance;
            }
        };

        CustomBuilder<T> builder = new CustomBuilder<T>(instanceType, supplier);

        fixture.addBuilderToStartOfPipeline(builder);
        fixture.customise(new OmitAutoPropertyCustomisation(instanceType));
    }
}
