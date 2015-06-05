package component.annotations;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.annotations.Range;
import com.flextrade.jfixture.rules.FixtureRule;
import org.junit.Rule;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class TestRangeAnnotation {

    @Rule
    public FixtureRule fc = FixtureRule.initFixtures();

    @Fixture
    @Range(min = 1000, max = 2000)
    private int number;

    @Test
    public void values_are_created_by_respecting_min_and_max_set_in_range_annotation() {
        assertThat(number, greaterThanOrEqualTo(1000));
        assertThat(number, lessThan(2000));
    }

    @Test // We want it to work for Dates but with Long min and max if used with @Range annotation
    public void dates_within_min_and_max_long_values_are_generated() throws ParseException {
        JFixture fixture = new JFixture();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date min = dateFormat.parse("2000-01-01");
        Date max = dateFormat.parse("2000-12-31");

        Date date = fixture.create().inRange(Date.class, min.getTime(), max.getTime());

        assertThat(date, greaterThanOrEqualTo(min));
        assertThat(date, lessThan(max));
    }
}