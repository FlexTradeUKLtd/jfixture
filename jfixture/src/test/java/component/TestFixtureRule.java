package component;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.rules.FixtureRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import testtypes.TypeWithProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestFixtureRule {

    private boolean fixtureFieldCreated = false;
    private static final String SYMBOL = "VOD.O";
    private JFixture fixture;
    {
        fixture = new JFixture();
        fixture.customise().sameInstance(String.class, SYMBOL);
    }

    @Rule
    public FixtureRule fixtureRule = FixtureRule.initFixtures(this.fixture);

    @Fixture
    private TypeWithProperties typeWithProperties;

    @Before
    public void before() {
        this.fixtureFieldCreated = this.typeWithProperties != null;
    }

    @Test
    public void rule_instantiates_all_fields_marked_with_fixture_attribute() {
        assertNotNull(this.typeWithProperties);
    }

    @Test
    public void rule_sets_properties_on_fields_marked_with_fixture_attribute_using_supplied_fixture() {
        assertEquals(SYMBOL, this.typeWithProperties.getSymbol());
        assertTrue(this.typeWithProperties.getSize() > 0);
    }

    @Test
    public void rule_is_run_before_any_at_before_methods() {
        assertTrue(this.fixtureFieldCreated);
    }

    @Test
    public void rule_exposes_instance_of_jfixture() {
        assertTrue(fixture == fixtureRule.getFixture());
    }
}
