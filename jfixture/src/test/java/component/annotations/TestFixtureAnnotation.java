package component.annotations;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.FixtureAnnotations;
import org.junit.Test;
import testtypes.TypeWithProperties;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestFixtureAnnotation {

    @Fixture
    private TypeWithProperties privateFieldWithAnnotation;

    @Fixture
    public TypeWithProperties publicFieldWithAnnotation;

    private TypeWithProperties fieldWithoutAnnotation;

    @Fixture
    private List<String> stringList;

    @Test
    public void initialises_auto_fixture_private_fields_automatically() {
        FixtureAnnotations.initFixtures(this);
        assertNotNull(privateFieldWithAnnotation);
    }

    @Test
    public void initialises_generic_auto_fixture_fields_automatically() {
        FixtureAnnotations.initFixtures(this);
        assertNotNull(stringList);
        assertNotEquals(0, stringList.size());
    }

    @Test
    public void initialises_auto_fixture_public_fields_automatically() {
        FixtureAnnotations.initFixtures(this);
        assertNotNull(publicFieldWithAnnotation);
    }

    @Test
    public void ignores_non_auto_fixture_fields() {
        FixtureAnnotations.initFixtures(this);
        assertNull(fieldWithoutAnnotation);
    }

    @Test
    public void initialises_auto_fixture_fields_automatically_using_the_supplied_fixture() {
        JFixture fixture = new JFixture();
        fixture.customise().sameInstance(String.class, "string");
        FixtureAnnotations.initFixtures(this, fixture);
        assertEquals("string", privateFieldWithAnnotation.getSymbol());
    }
}
