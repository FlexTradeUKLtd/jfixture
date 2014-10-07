package com.flextrade.jfixture;

import java.util.ArrayList;

class FixtureBehaviours implements BehavioursContainer {

    private final FinalBuilderContainer finalBuilderContainer;
    private final SpecimenBuilder rootBuilder;
    private final ArrayList<FixtureBehaviour> allBehaviours = new ArrayList<FixtureBehaviour>();

    public FixtureBehaviours(FinalBuilderContainer finalBuilderContainer, SpecimenBuilder rootBuilder) {
        this.finalBuilderContainer = finalBuilderContainer;
        this.rootBuilder = rootBuilder;
    }

    public void add(FixtureBehaviour behaviour) {
        this.allBehaviours.add(behaviour);
        this.resetFinalBuilder();
    }

    public boolean remove(Class<? extends FixtureBehaviour> classToRemove) {
        FixtureBehaviour behaviourToRemove = find(classToRemove);
        if(behaviourToRemove != null) {
            this.allBehaviours.remove(behaviourToRemove);
            this.resetFinalBuilder();
            return true;
        }

        return false;
    }

    public <T extends FixtureBehaviour> T find(Class<T> clazz) {
        for (FixtureBehaviour t : this.allBehaviours) {
            if (t.getClass().equals(clazz)) {
                return (T)t;
            }
        }

        return null;
    }

    private void resetFinalBuilder() {
        CompositeBehaviour compositeBehaviour = new CompositeBehaviour(this.allBehaviours);
        this.finalBuilderContainer.setFinalBuilder(compositeBehaviour.transform(this.rootBuilder));
    }
}
