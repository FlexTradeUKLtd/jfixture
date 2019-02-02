package component.generics;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

// These tests mostly come from use cases that have
// been found to cause errors. Due to Type Erasure
// in the JVM handling generics is quite complex
// so I want to a keep a suite of tests that cover
// issues as they arise to ensure we don't have a regression
public class TestComplexGenerics {

    JFixture fixture = new JFixture();

    @Test
    @SuppressWarnings("ConstantConditions")
    public void can_construct_types_with_multiple_levels_of_generic_types_with_a_list() {
        SpecimenType<FooWithBarWithList<String>> type = new SpecimenType<FooWithBarWithList<String>>(){};
        FooWithBarWithList<String> instance = fixture.create(type);
        assertNotNull(instance);
        assertNotNull(instance.bar);
        assertNotNull(instance.bar.list);
        assertTrue(instance.bar.list.size() > 0);
        assertTrue(instance.bar.list.get(0) instanceof String);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void can_construct_types_with_multiple_levels_of_generic_types_with_a_single_value() {
        SpecimenType<FooWithBarWithValue<String>> type = new SpecimenType<FooWithBarWithValue<String>>(){};
        FooWithBarWithValue<String> instance = fixture.create(type);
        assertNotNull(instance);
        assertNotNull(instance.bar);
        assertNotNull(instance.bar.value);
        assertTrue(instance.bar.value instanceof String);
    }

    public static class FooWithBarWithList<T> {
        private BarWithList<T> bar;
        public FooWithBarWithList(BarWithList<T> bar) {
            this.bar = bar;
        }
    }

    public static class BarWithList<T> {
        private List<T> list;
        public BarWithList(List<T> list) {
            this.list = list;
        }
    }

    public static class FooWithBarWithValue<T> {
        private BarWithValue<T> bar;
        public FooWithBarWithValue(BarWithValue<T> bar) {
            this.bar = bar;
        }
    }

    public static class BarWithValue<T> {
        private T value;
        public BarWithValue(T value) {
            this.value = value;
        }
    }
}
