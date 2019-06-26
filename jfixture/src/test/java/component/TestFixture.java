package component;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import org.junit.Test;
import testtypes.TypeWithBooleanConvention;
import testtypes.TypeWithCircularReference;
import testtypes.TypeWithFields;
import testtypes.TypeWithFieldWithThrowingConstructor;
import testtypes.TypeWithProperties;
import testtypes.constructors.TypeWithConstructor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestFixture {

    @Test
    public void type_with_constructor_resolves_smallest_constructor() {
        JFixture fixture = new JFixture();
        TypeWithConstructor type = fixture.create(TypeWithConstructor.class);

        assertNotNull(type);
        assertNotNull(type.getSymbol());
        assertTrue(type.getSize() > 0);
    }

    @Test
    public void type_with_fields_sets_all_fields() {
        JFixture fixture = new JFixture();
        TypeWithFields type = fixture.create(TypeWithFields.class);

        assertNotNull(type);
        assertNotNull(type.symbol);
        assertTrue(type.size > 0);
    }

    @Test
    public void type_with_properties_sets_all_properties() {
        JFixture fixture = new JFixture();
        TypeWithProperties type = fixture.create(TypeWithProperties.class);

        assertNotNull(type);
        assertNotNull(type.getSymbol());
        assertTrue(type.getSize() > 0);
    }

    @Test
    public void type_with_boolean_convention_resolves_the_boolean_correctly() {
        JFixture fixture = new JFixture();
        TypeWithBooleanConvention type = fixture.create(TypeWithBooleanConvention.class);
        assertTrue(type.isEnabled());
    }

    @Test(expected = ObjectCreationException.class)
    public void applies_throwing_recursion_behaviour_by_default() {
        JFixture fixture = new JFixture();
        fixture.create(TypeWithCircularReference.class);
    }

    @Test
    public void array_of_type_containing_object_with_throwing_constructor_sets_fields_with_working_constructor() {
        JFixture fixture = new JFixture();
        TypeWithFieldWithThrowingConstructor[] types = fixture.create(TypeWithFieldWithThrowingConstructor[].class);

        assertNotNull(types);
        assertTrue(types.length > 0);
        assertNotNull(types[0]);
        assertNotNull(types[0].fieldWithThrowingConstructor);
    }
}
