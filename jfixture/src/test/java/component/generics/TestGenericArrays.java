package component.generics;

import com.flextrade.jfixture.JFixture;
import org.junit.Test;
import testtypes.TypeWithGenericArray;

import static org.junit.Assert.assertTrue;

public class TestGenericArrays {

    @Test
    public void arrays_are_populated_when_type_also_contains_generics() {
        // This is a weird one - bug in 1.6 compiler treats arrays as
        // different type than 1.7+ so we need to cater for this
        JFixture fixture = new JFixture();
        TypeWithGenericArray type = fixture.create(TypeWithGenericArray.class);
        assertTrue(type.getInts().length > 0);
    }
}
