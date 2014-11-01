package component.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.customisation.InstanceCustomisation;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Test;
import testtypes.TypeWithProperties;
import testtypes.generic.TypeWithGenericField;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;

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
        final TypeWithGenericField<String> type = new TypeWithGenericField<String>();
        type.value = "string";
        JFixture fixture = new JFixture();
        fixture.customise(new InstanceCustomisation(new SpecimenType<TypeWithGenericField<String>>() {}, type))  ;

        TypeWithGenericField<String> specimen =  fixture.create(new SpecimenType<TypeWithGenericField<String>>(){});
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
}
