package component.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.customisation.SubTypeCustomisation;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestSubTypeCustomisation {

    @Test
    public void returns_instance_of_sub_type_if_base_type_is_requested() {
        JFixture fixture = new JFixture();
        fixture.customise(new SubTypeCustomisation(List.class, LinkedList.class));
        List list = fixture.create(SpecimenType.of(List.class));
        assertTrue(list instanceof LinkedList);
    }
}
