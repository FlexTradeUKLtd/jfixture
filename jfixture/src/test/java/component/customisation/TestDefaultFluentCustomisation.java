package component.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.SpecimenSupplier;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Test;
import testtypes.TypeWithCircularReference;
import testtypes.TypeWithList;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestDefaultFluentCustomisation {

    @Test
    public void same_instance_adds_instance_per_type_customisation() {

        final String instance = "string";
        JFixture fixture = new JFixture();
        fixture.customise().sameInstance(String.class, instance);

        String first = fixture.create(String.class);
        String second = fixture.create(String.class);
        assertEquals(instance, first);
        assertEquals(instance, second);
    }

    @Test
    public void lazy_instance_adds_instance_with_factory_method_customisation() {
        JFixture fixture = new JFixture();
        fixture.customise().lazyInstance(Integer.class, new SpecimenSupplier<Integer>() {
            int count = 5;
            @Override
            public Integer create() {
                return count--;
            }
        });

        int first = fixture.create(int.class);
        assertEquals(5, first);
        int second = fixture.create(int.class);
        assertEquals(4, second);
    }

    @Test
    public void use_sub_type_adds_sub_type_customisation() {
        JFixture fixture = new JFixture();
        fixture.customise().useSubType(Number.class, BigDecimal.class);

        Number number = fixture.create(Number.class);
        assertTrue(number instanceof BigDecimal);
    }

    @Test
    public void customisations_can_be_chained() {
        JFixture fixture = new JFixture();

        fixture.customise()
                .sameInstance(String.class, "string")
                .sameInstance(int.class, 10);

        assertEquals("string", fixture.create(String.class));
        int value = fixture.create(int.class);
        assertEquals(10, value);
    }

    @Test(expected = ObjectCreationException.class)
    public void recursion_behaviour_can_be_set_to_throw_exception() {
        JFixture fixture = new JFixture();
        fixture.customise().circularDependencyBehaviour().omitSpecimen(); // Need to omit first because it defaults to throwing
        fixture.customise().circularDependencyBehaviour().throwException();
        fixture.create(TypeWithCircularReference.class);
    }

    @Test
    public void recursion_behaviour_can_be_set_to_omit_specimen() {
        JFixture fixture = new JFixture();
        fixture.customise().circularDependencyBehaviour().omitSpecimen();
        TypeWithCircularReference instance = fixture.create(TypeWithCircularReference.class);
        assertNotNull(instance);
        assertNull(instance.circular);
    }

    @Test
    public void repeat_count_can_be_set() {
        JFixture fixture = new JFixture();
        fixture.customise().repeatCount(5);
        TypeWithList instance = fixture.create(SpecimenType.of(TypeWithList.class));
        assertEquals(5, instance.getList().size());
    }
}
