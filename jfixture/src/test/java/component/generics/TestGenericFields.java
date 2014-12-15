package component.generics;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;
import testtypes.generic.TypeWithGenericField;

import java.math.BigDecimal;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class TestGenericFields {

    private JFixture fixture;

    @Before
    public void initialise() {
        fixture = new JFixture();
    }

    @Test
    public void instantiates_generic_types_with_generic_fields() {
        TypeWithGenericField<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericField<String, BigDecimal>>() {});
        assertNotNull(type);
    }

    @Test
    public void populates_fields_with_simple_generic_type() {
        TypeWithGenericField<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericField<String, BigDecimal>>() {});
        assertNotNull(type.valueT);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_nested_generic_type() {
        TypeWithGenericField<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericField<String, BigDecimal>>() {});
        assertNotNull(type.genericListT);
        assertTrue(type.genericListT.size() > 0);
        assertTrue(type.genericListT.get(0) instanceof String);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_nested_second_generic_type() {
        TypeWithGenericField<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericField<String, BigDecimal>>() {});
        assertNotNull(type.genericListU);
        assertTrue(type.genericListU.size() > 0);
        assertTrue(type.genericListU.get(0) instanceof BigDecimal);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_nested_hard_coded_generic_type() {
        TypeWithGenericField<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericField<String, BigDecimal>>() {});
        assertNotNull(type.typedList);
        assertTrue(type.typedList.size() > 0);
        assertTrue(type.typedList.get(0) instanceof Double);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_doubly_nested_generic_type() {
        TypeWithGenericField<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericField<String, BigDecimal>>() {});
        assertNotNull(type.genericListOfListT);
        assertTrue(type.genericListOfListT.size() > 0);
        assertTrue(type.genericListOfListT.get(0).size() > 0);
        assertTrue(type.genericListOfListT.get(0).get(0) instanceof String);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_doubly_nested_hard_coded_generic_type() {
        TypeWithGenericField<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericField<String, BigDecimal>>() {});
        assertNotNull(type.genericTypedListOfList);
        assertTrue(type.genericTypedListOfList.size() > 0);
        assertTrue(type.genericTypedListOfList.get(0).size() > 0);
        assertTrue(type.genericTypedListOfList.get(0).get(0) instanceof Double);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_nested_types_with_multiple_generic_arguments() {
        TypeWithGenericField<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericField<String, BigDecimal>>() {});
        assertNotNull(type.genericListOfMap);
        assertTrue(type.genericListOfMap.size() > 0);
        assertTrue(type.genericListOfMap.get(0).size() > 0);
        Map.Entry outerMapFirstEntry = type.genericListOfMap.get(0).entrySet().iterator().next();
        assertTrue(outerMapFirstEntry.getKey() instanceof String);
        assertTrue(outerMapFirstEntry.getValue() instanceof BigDecimal);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_nested_types_with_multiple_partially_bounded_generic_arguments() {
        TypeWithGenericField<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericField<String, BigDecimal>>() {});
        assertNotNull(type.genericPartiallyBound);
        assertTrue(type.genericPartiallyBound.size() > 0);
        Map.Entry outerMapFirstEntry = type.genericPartiallyBound.entrySet().iterator().next();
        Map.Entry innerMapFirstEntry = ((Map<?, ?>) outerMapFirstEntry.getValue()).entrySet().iterator().next();

        assertTrue(innerMapFirstEntry.getKey() instanceof Integer);
        assertTrue(innerMapFirstEntry.getValue() instanceof BigDecimal);
    }
}
