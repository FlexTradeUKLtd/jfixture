package component.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.customisation.InstanceCustomisation;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Test;
import testtypes.TypeWithProperties;
import testtypes.generic.TypeWithGenericField;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class TestInstanceCustomisation {

    @Test
    public void always_returns_same_instance_for_each_request_of_that_type() {
        final String value = "string";
        JFixture fixture = new JFixture();
        fixture.customise(new InstanceCustomisation<String>(String.class, value));
        assertEquals(value, fixture.create(String.class));
        assertEquals(value, fixture.create(String.class));
    }

    @Test
    public void always_returns_same_instance_of_generic_type_for_each_request_of_that_type() {
        final TypeWithGenericField<String, Double> type = new TypeWithGenericField<String, Double>();
        type.valueT = "string";
        JFixture fixture = new JFixture();
        fixture.customise(new InstanceCustomisation(new SpecimenType<TypeWithGenericField<String, Double>>() {}, type))  ;

        TypeWithGenericField<String, Double> specimen =  fixture.create(new SpecimenType<TypeWithGenericField<String, Double>>(){});
        assertSame(type, specimen);
    }

    @Test
    public void does_not_auto_populate_properties_of_instance() {
        TypeWithProperties instance = new TypeWithProperties();
        JFixture fixture = new JFixture();
        fixture.customise(new InstanceCustomisation<TypeWithProperties>(TypeWithProperties.class, instance));

        TypeWithProperties specimen = fixture.create(TypeWithProperties.class);

        assertNull(specimen.getSymbol());
        assertEquals(0, specimen.getSize());
    }

    @Test
    public void instance_customisation_works_will_null_instances_for_generic_types() {
        JFixture fixture = new JFixture();
        fixture.customise(new InstanceCustomisation<List<Contained>>(new SpecimenType<List<Contained>>(){}, null));

        Container container = fixture.create(Container.class);

        assertNull(container.list);
    }

    static class Container {
        final List<Contained> list;

        Container(List<Contained> list) {
            this.list = list;
        }
    }

    static class Contained {
        final String name;

        Contained(String name) {
            this.name = name;
        }
    }
}
