package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;

import java.util.Random;

class CharacterGenerator implements SpecimenBuilder {

    private final Random random = new Random();

    @Override
    public Object create(Object request, SpecimenContext context) {

        if (!request.equals(Character.class)) {
            return new NoSpecimen();
        }

        return (char) (random.nextInt(26) + 'a');
    }
}
