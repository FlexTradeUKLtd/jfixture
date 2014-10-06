package component.annotations;

import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.annotations.FromListOf;
import com.flextrade.jfixture.rules.FixtureRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestFromListAnnotation {

    @Rule
    public FixtureRule fc = FixtureRule.initFixtures();

    @Fixture
    @FromListOf(strings = {"a", "b", "c"})
    private String letter;

    @Fixture
    @FromListOf(numbers = {1, 2, 3})
    private Long number;

    @Test
    public void string_value_is_same_as_one_element_in_list() {
        assertTrue(letter.equals("a") ||
                   letter.equals("b") ||
                   letter.equals("c"));
    }

    @Test
    public void number_value_is_same_as_one_element_in_list() {
        assertTrue(number.equals(1L) ||
                   number.equals(2L) ||
                   number.equals(3L));
    }
}
