package com.flextrade.jfixture.behaviours.specimentype;

import com.flextrade.jfixture.SpecimenBuilder;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class TestSpecimenTypeInjectorBehaviour {

    @Test
    public void transform_returns_instance_of_specimen_type_injector() {
        SpecimenTypeInjectorBehaviour behaviour = new SpecimenTypeInjectorBehaviour();
        SpecimenBuilder transformed = behaviour.transform(mock(SpecimenBuilder.class));
        assertTrue(transformed instanceof SpecimenTypeInjector);
    }
}