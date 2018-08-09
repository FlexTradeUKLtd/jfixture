package com.flextrade.jfixture.rules;

import java.lang.reflect.Type;

import com.flextrade.jfixture.FluentCustomisation;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.SpecimenSupplier;
import com.flextrade.jfixture.customisation.Customisation;
import com.flextrade.jfixture.customisation.fluent.AutoPropertyBehaviour;
import com.flextrade.jfixture.customisation.fluent.NoResolutionBehaviour;
import com.flextrade.jfixture.customisation.fluent.RecursionBehaviour;
import com.flextrade.jfixture.utility.Interceptor;
import com.flextrade.jfixture.utility.Transformer;

public class FixtureRuleCustomisation extends FixtureRule implements FluentCustomisation {

    private FluentCustomisation customisation;

    FixtureRuleCustomisation(JFixture fixture) {
        super(fixture);
        customisation = fixture.customise();
    }

    @Override
    public FixtureRuleCustomisation repeatCount(int count) {
        customisation.repeatCount(count);
        return this;
    }

    @Override
    public <T> FixtureRuleCustomisation sameInstance(Type type, T instance) {
        customisation.sameInstance(type, instance);
        return this;
    }

    @Override
    public <T> FixtureRuleCustomisation sameInstance(Class<T> clazz, T instance) {
        customisation.sameInstance(clazz, instance);
        return this;
    }

    @Override
    public <T> FixtureRuleCustomisation lazyInstance(Type type, SpecimenSupplier<? extends T> supplier) {
        customisation.lazyInstance(type, supplier);
        return this;
    }

    @Override
    public <T> FixtureRuleCustomisation lazyInstance(Class<T> clazz, SpecimenSupplier<? extends T> supplier) {
        customisation.lazyInstance(clazz, supplier);
        return this;
    }

    @Override
    public FluentCustomisation propertyOf(Class<?> clazz, String name, Object value) {
        customisation.propertyOf(clazz, name, value);
        return this;
    }

    @Override
    public <T, U extends T> FixtureRuleCustomisation useSubType(Class<T> baseClass, Class<U> subClass) {
        customisation.useSubType(baseClass, subClass);
        return this;
    }

    @Override
    public FixtureRuleCustomisation add(Customisation customisation) {
        this.customisation.add(customisation);
        return this;
    }

    @Override
    public <T> FixtureRuleCustomisation intercept(Class<T> clazz, Interceptor<T> interceptor) {
        customisation.intercept(clazz, interceptor);
        return this;
    }

    @Override
    public <T> FluentCustomisation transform(Class<T> clazz, Transformer<T> transformer) {
        customisation.transform(clazz, transformer);
        return this;
    }

    @Override
    public RecursionBehaviour circularDependencyBehaviour() {
        return customisation.circularDependencyBehaviour();
    }

    @Override
    public NoResolutionBehaviour noResolutionBehaviour() {
        return customisation.noResolutionBehaviour();
    }

    @Override
    public AutoPropertyBehaviour autoPropertyBehaviour() {
        return customisation.autoPropertyBehaviour();
    }
}
