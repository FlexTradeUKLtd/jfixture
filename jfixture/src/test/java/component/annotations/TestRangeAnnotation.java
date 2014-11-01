package component.annotations;

import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.annotations.Range;
import com.flextrade.jfixture.rules.FixtureRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestRangeAnnotation {

    @Rule
    public FixtureRule fc = FixtureRule.initFixtures();

    @Fixture
    @Range(min = 1000, max = 2000)
    private int number;

    @Test
    public void values_are_created_by_respecting_min_and_max_set_in_range_annotation() {
        assertTrue(number >= 1000 && number < 2000);
    }
}
