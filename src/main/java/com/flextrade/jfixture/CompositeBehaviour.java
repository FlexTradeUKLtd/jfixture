package com.flextrade.jfixture;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CompositeBehaviour implements FixtureBehaviour {

    private final Iterable<FixtureBehaviour> behaviours;

    @Override
    public SpecimenBuilder transform(SpecimenBuilder builder) {

        SpecimenBuilder enrichedBuilder = builder;
        for (FixtureBehaviour behaviour : this.behaviours) {
            enrichedBuilder = behaviour.transform(enrichedBuilder);
        }

        return enrichedBuilder;
    }
}
