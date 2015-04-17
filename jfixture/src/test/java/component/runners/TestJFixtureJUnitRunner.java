package component.runners;

import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.runners.JFixtureJUnitRunner;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(JFixtureJUnitRunner.class)
public class TestJFixtureJUnitRunner {

    @Fixture String value;

    @Test
    public void runner_instantiates_fields_marked_with_fixture_annotation() {
        assertNotNull(value);
    }

    @Test
    public void runner_supplies_arguments_to_test_methods(int primitive, String type, List<String> generic) {
        assertThat(primitive, Matchers.greaterThan(0));
        assertThat(type, Matchers.notNullValue());
        assertThat(generic, Matchers.notNullValue());
        assertThat(generic.size(), Matchers.greaterThan(0));
    }
}
