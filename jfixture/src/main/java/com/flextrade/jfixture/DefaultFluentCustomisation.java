package com.flextrade.jfixture;

import com.flextrade.jfixture.customisation.Customisation;
import com.flextrade.jfixture.customisation.InterceptingCustomisation;
import com.flextrade.jfixture.customisation.InstanceCustomisation;
import com.flextrade.jfixture.customisation.InstanceFactoryCustomisation;
import com.flextrade.jfixture.customisation.OverridePropertyCustomisation;
import com.flextrade.jfixture.customisation.SubTypeCustomisation;
import com.flextrade.jfixture.customisation.TransformingCustomisation;
import com.flextrade.jfixture.customisation.fluent.AutoPropertyBehaviour;
import com.flextrade.jfixture.customisation.fluent.NoResolutionBehaviour;
import com.flextrade.jfixture.customisation.fluent.RecursionBehaviour;
import com.flextrade.jfixture.utility.Interceptor;
import com.flextrade.jfixture.utility.Transformer;

import java.lang.reflect.Type;

class DefaultFluentCustomisation implements FluentCustomisation {

    private final CustomisationContainer customisationContainer;
    private final MultipleCount multipleCount;

    public DefaultFluentCustomisation(CustomisationContainer customisationContainer, MultipleCount multipleCount) {
        this.customisationContainer = customisationContainer;
        this.multipleCount = multipleCount;
    }

    public FluentCustomisation repeatCount(int count) {
        this.multipleCount.setCount(count);
        return this;
    }

    public <T> FluentCustomisation sameInstance(Type type, T instance) {
        this.customisationContainer.customise(new InstanceCustomisation<T>(type, instance));
        return this;
    }

    public <T> FluentCustomisation sameInstance(Class<T> clazz, T instance) {
        this.customisationContainer.customise(new InstanceCustomisation<T>(clazz, instance));
        return this;
    }

    public <T> FluentCustomisation lazyInstance(Type type, SpecimenSupplier<? extends T> supplier) {
        this.customisationContainer.customise(new InstanceFactoryCustomisation<T>(type, supplier));
        return this;
    }

    public <T> FluentCustomisation lazyInstance(Class<T> clazz, SpecimenSupplier<? extends T> supplier) {
        this.customisationContainer.customise(new InstanceFactoryCustomisation<T>(clazz, supplier));
        return this;
    }

    @Override
    public FluentCustomisation propertyOf(Class<?> clazz, String name, Object value) {
        this.customisationContainer.customise(new OverridePropertyCustomisation(clazz, name, value));
        return this;
    }

    public <T, U extends T> FluentCustomisation useSubType(Class<T> baseClass, Class<U> subClass) {
        this.customisationContainer.customise(new SubTypeCustomisation(baseClass, subClass));
        return this;
    }

    public FluentCustomisation add(Customisation customisation) {
        this.customisationContainer.customise(customisation);
        return this;
    }

    @Override
    public <T> FluentCustomisation intercept(Class<T> classToIntercept, Interceptor<T> interceptor) {
        this.customisationContainer.customise(new InterceptingCustomisation<T>(classToIntercept, interceptor));
        return this;
    }

    @Override
    public <T> FluentCustomisation transform(Class<T> classToIntercept, Transformer<T> transformer) {
        this.customisationContainer.customise(new TranformingCustomisation<T>(classToIntercept, transformer));
        return this;
    }

    public RecursionBehaviour circularDependencyBehaviour() {
        return new RecursionBehaviour(this.customisationContainer, this);
    }

    public NoResolutionBehaviour noResolutionBehaviour() {
        return new NoResolutionBehaviour(this.customisationContainer, this);
    }

    public AutoPropertyBehaviour autoPropertyBehaviour() {
        return new AutoPropertyBehaviour(this.customisationContainer, this);
    }
}
