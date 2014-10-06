package com.flextrade.jfixture;

import com.flextrade.jfixture.behaviours.specimentype.SpecimenTypeInjectorBehaviour;
import com.flextrade.jfixture.builders.CompositeBuilder;
import com.flextrade.jfixture.builders.DefaultEngineParts;
import com.flextrade.jfixture.customisation.AutoPropertyCustomisation;
import com.flextrade.jfixture.customisation.Customisation;
import com.flextrade.jfixture.customisation.ThrowOnNoResolutionCustomisation;
import com.flextrade.jfixture.customisation.ThrowingRecursionCustomisation;
import com.flextrade.jfixture.extensions.CreateExtensions;
import com.flextrade.jfixture.extensions.CreateExtensionsImpl;

import java.lang.reflect.Type;
import java.util.Collection;

public class JFixture implements SpecimenCreator, SpecimenBuilderPipeline, BehaviourProvider, CustomisationContainer {

    private final CompositeBuilder engine;
    private final FinalBuilderContainer finalBuilderContainer;
    private final FixtureBehaviours behaviours;
    private final FixtureCollections collections;
    private final FluentCustomisation customisation;
    private final CreateExtensions createExtensions;

    public JFixture() {
        DefaultEngineParts defaultEngineParts = new DefaultEngineParts();
        MultipleCount multipleCount = defaultEngineParts.multipleCount();
        Collection<SpecimenBuilder> builders = defaultEngineParts.builders();

        this.engine = new CompositeBuilder(builders);
        this.finalBuilderContainer = new FinalBuilderContainer();
        this.behaviours = new FixtureBehaviours(this.finalBuilderContainer, this.engine);
        this.collections = new FixtureCollections(this, multipleCount);
        this.customisation = new DefaultFluentCustomisation(this, multipleCount);
        this.createExtensions = new CreateExtensionsImpl(this.finalBuilderContainer);

        this.applyDefaultCustomisations();
    }

    public FluentCustomisation customise() {
        return this.customisation;
    }

    public FixtureCollections collections() {
        return this.collections;
    }

    public BehavioursContainer behaviours() {
        return this.behaviours;
    }

    public JFixture customise(Customisation customisation) {
        customisation.customise(this);
        return this;
    }

    public JFixture addBuilderToStartOfPipeline(SpecimenBuilder builder) {
        this.engine.add(0, builder);
        return this;
    }

    public JFixture addBuilderToEndOfPipeline(SpecimenBuilder builder) {
        this.engine.add(builder);
        return this;
    }

    public CreateExtensions create() {
        return this.createExtensions;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Type type) {
        return (T) this.create((Object) type);
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> clazz) {
        return (T) this.create((Object) clazz);
    }

    @SuppressWarnings("unchecked")
    Object create(Object object) {
        Object result = this.finalBuilderContainer.getBuilder().create(object, this.finalBuilderContainer.getContext());
        if (result instanceof NoSpecimen) return null;
        return result;
    }

    private void applyDefaultCustomisations() {
        this.behaviours().add(new SpecimenTypeInjectorBehaviour());
        this.customise(new ThrowingRecursionCustomisation());
        this.customise(new ThrowOnNoResolutionCustomisation());
        this.customise(new AutoPropertyCustomisation());
    }
}