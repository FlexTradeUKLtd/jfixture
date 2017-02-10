package component.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import org.junit.Test;
import sun.util.BuddhistCalendar;
import testtypes.TypeWithProperties;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestOverrideProperties {

    @Test
    public void a_property_of_a_class_can_be_overridden_with_a_specific_instance_of_the_property_type() {
        JFixture fixture = new JFixture();
        fixture.customise().propertyOf(OverridePropertiesType.class, "Size", -123);

        OverridePropertiesType type = fixture.create(OverridePropertiesType.class);

        assertEquals(-123, type.getSize());
    }

    @Test
    public void properties_are_only_overridden_on_the_defined_type() {
        JFixture fixture = new JFixture();
        fixture.customise().propertyOf(OverridePropertiesType.class, "Size", -123);

        TypeWithProperties type = fixture.create(TypeWithProperties.class); // TypeWithProperties also has a Size, but that shouldn't get overridden

        assertNotEquals(-123, type.getSize());
    }

    @Test
    public void a_property_of_a_class_can_be_overridden_with_full_setter_name() {
        JFixture fixture = new JFixture();
        fixture.customise().propertyOf(OverridePropertiesType.class, "setSize", -123);

        OverridePropertiesType type = fixture.create(OverridePropertiesType.class);

        assertEquals(-123, type.getSize());
    }

    @Test
    public void a_property_of_a_class_can_be_overridden_with_a_specific_instance_of_the_property_sub_type() {
        JFixture fixture = new JFixture();

        Calendar buddhistCalendar = new BuddhistCalendar();
        fixture.customise().propertyOf(OverridePropertiesType.class, "Calendar", buddhistCalendar);

        OverridePropertiesType type = fixture.create(OverridePropertiesType.class);

        assertEquals(buddhistCalendar, type.getCalendar());
    }

    @Test(expected = ObjectCreationException.class)
    public void throws_exception_if_overriding_instance_is_not_assignable_to_property_type() {
        JFixture fixture = new JFixture();
        fixture.customise().propertyOf(OverridePropertiesType.class, "Size", "not a number");

        fixture.create(OverridePropertiesType.class);
    }

    public class OverridePropertiesType {

        private Calendar calendar;
        private int size;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Calendar getCalendar() {
            return calendar;
        }

        public void setCalendar(Calendar calendar) {
            this.calendar = calendar;
        }
    }
}
