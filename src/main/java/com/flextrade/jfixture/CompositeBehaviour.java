package com.flextrade.jfixture;

class CompositeBehaviour implements FixtureBehaviour {

    private final Iterable<FixtureBehaviour> behaviours;

    public CompositeBehaviour(Iterable<FixtureBehaviour> behaviours) {
        this.behaviours = behaviours;
    }

    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {

        SpecimenBuilder enrichedBuilder = builder;
        for (FixtureBehaviour behaviour : this.behaviours) {
            enrichedBuilder = behaviour.transform(enrichedBuilder);
        }

        return enrichedBuilder;
    }
}
