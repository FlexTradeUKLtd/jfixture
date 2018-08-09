package com.flextrade.jfixture;

import com.flextrade.jfixture.customisation.Customisation;
import com.flextrade.jfixture.customisation.fluent.AutoPropertyBehaviour;
import com.flextrade.jfixture.customisation.fluent.NoResolutionBehaviour;
import com.flextrade.jfixture.customisation.fluent.RecursionBehaviour;
import com.flextrade.jfixture.utility.Interceptor;
import com.flextrade.jfixture.utility.Transformer;

import java.lang.reflect.Type;

public interface FluentCustomisation {

    FluentCustomisation repeatCount(int count);

    <T> FluentCustomisation sameInstance(Type type, T instance);

    <T> FluentCustomisation sameInstance(Class<T> clazz, T instance);

    <T> FluentCustomisation lazyInstance(Type type, SpecimenSupplier<? extends T> supplier);

    <T> FluentCustomisation lazyInstance(Class<T> clazz, SpecimenSupplier<? extends T> supplier);

    FluentCustomisation propertyOf(Class<?> clazz, String name, Object value);

    <T, U extends T> FluentCustomisation useSubType(Class<T> baseClass, Class<U> subClass);

    FluentCustomisation add(Customisation customisation);

    <T> FluentCustomisation intercept(Class<T> clazz, Interceptor<T> interceptor);

    <T> FluentCustomisation transform(Class<T> classToIntercept, Transformer<T> interceptor);

    RecursionBehaviour circularDependencyBehaviour();

    NoResolutionBehaviour noResolutionBehaviour();

    AutoPropertyBehaviour autoPropertyBehaviour();
}
