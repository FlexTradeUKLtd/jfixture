package component.extensions;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestCreateInRange {

    @Test
    public void result_is_within_supplied_range() {
        JFixture fixture = new JFixture();
        Long value = fixture.create().inRange(Long.class, 1000L, 2000L);

        assertTrue(value >= 1000 && value < 2000);
    }

    @Test(expected = ObjectCreationException.class)
    public void invalid_limits_throws_exception() {
        JFixture fixture = new JFixture();
        fixture.create().inRange(Long.class, 10L, 1L); // min > max
    }
}
