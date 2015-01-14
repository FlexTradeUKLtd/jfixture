package component;

import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import org.junit.Test;
import testtypes.TypeWithFields;
import testtypes.TypeWithProperties;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestPrefixedValues {

    @Fixture
    private String symbol;

    @Test
    public void string_properties_have_the_property_name_prefixed() {
        JFixture fixture = new JFixture();
        TypeWithProperties type = fixture.create(TypeWithProperties.class);

        String symbolProperty = type.getSymbol();
        assertEquals("Symbol", symbolProperty.substring(0, 6));

        String uuid = symbolProperty.substring(6, 42);
        assertNotNull(UUID.fromString(uuid));
    }

    @Test
    public void string_fields_have_their_name_prefixed() {
        JFixture fixture = new JFixture();
        TypeWithFields type = fixture.create(TypeWithFields.class);

        String symbolField = type.symbol;
        assertEquals("symbol", symbolField.substring(0, 6));

        String uuid = symbolField.substring(6, 42);
        assertNotNull(UUID.fromString(uuid));
    }

    @Test
    public void Fixture_string_fields_have_their_name_prefixed() {
        FixtureAnnotations.initFixtures(this);

        assertEquals("symbol", symbol.substring(0, 6));

        String uuid = symbol.substring(6, 42);
        assertNotNull(UUID.fromString(uuid));
    }

    @Test
    public void Overriding_string_instance_causes_no_prefix_to_be_added() {
        JFixture fixture = new JFixture();
        fixture.customise().sameInstance(String.class, "string");
        TypeWithProperties type = fixture.create(TypeWithProperties.class);

        assertEquals("string", type.getSymbol());
    }
}
