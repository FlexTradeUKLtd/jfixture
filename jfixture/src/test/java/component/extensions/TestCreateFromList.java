package component.extensions;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestCreateFromList {

    @Test
    public void result_is_one_of_item_in_collection() {
        JFixture fixture = new JFixture();
        String value = fixture.create().fromList("a", "b", "c");

        assertTrue(value.equals("a") || value.equals("b") || value.equals("c"));
    }

    @Test(expected = ObjectCreationException.class)
    public void empty_list_throws_exception() {
        JFixture fixture = new JFixture();
        fixture.create().fromList();
    }
}
