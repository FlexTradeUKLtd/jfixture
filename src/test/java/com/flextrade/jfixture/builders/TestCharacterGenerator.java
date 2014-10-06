package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.NoSpecimen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCharacterGenerator {

    private CharacterGenerator characterGenerator;

    @Before
    public void initialise() {
        characterGenerator = new CharacterGenerator();
    }

    @Test
    public void non_character_type_returns_no_specimen() {
        Object result = this.characterGenerator.create(Integer.class, null);
        assertEquals(new NoSpecimen(), result);
    }

    @Test
    public void character_type_request_returns_a_character() {
        Object result = this.characterGenerator.create(Character.class, null);
        assertTrue(result instanceof Character);
    }
}
