package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.autoproperty.AutoPropertyBehaviour;
import com.flextrade.jfixture.specifications.InverseSpecification;
import com.flextrade.jfixture.specifications.NeverSpecification;
import com.flextrade.jfixture.specifications.Specification;
import com.flextrade.jfixture.specifications.TypeInHierarchySpecification;

import java.lang.reflect.Type;

public class OmitAutoPropertyCustomisation implements Customisation {

    private final Specification specification;

    public OmitAutoPropertyCustomisation() {
        this.specification = new NeverSpecification();
    }

    public OmitAutoPropertyCustomisation(Type type) {
        this.specification = new InverseSpecification(new TypeInHierarchySpecification(type));
    }

    @Override
    public void customise(JFixture fixture) {
        AutoPropertyBehaviour apb = fixture.behaviours().find(AutoPropertyBehaviour.class);
        if (apb == null) return;

        apb.specifications().add(this.specification);
    }
}
