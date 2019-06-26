package component.generics;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;
import testtypes.generic.TypeWithGenericMethod;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestGenericMethods {

    private JFixture fixture;

    @Before
    public void initialise() {
        fixture = new JFixture();
    }

    @Test
    public void instantiates_generic_types_with_generic_setters() {
        TypeWithGenericMethod<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericMethod<String, BigDecimal>>(){});
        assertNotNull(type);
    }

    @Test
    public void populates_setters_with_simple_generic_type() {
        TypeWithGenericMethod<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericMethod<String, BigDecimal>>(){});
        assertNotNull(type.getValueT());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_setters_with_nested_generic_type() {
        TypeWithGenericMethod<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericMethod<String, BigDecimal>>(){});
        assertNotNull(type.getGenericListT());
        assertTrue(type.getGenericListT().size() > 0);
        assertTrue(type.getGenericListT().get(0) instanceof String);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_setters_with_nested_second_generic_type() {
        TypeWithGenericMethod<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericMethod<String, BigDecimal>>(){});
        assertNotNull(type.getGenericListU());
        assertTrue(type.getGenericListU().size() > 0);
        assertTrue(type.getGenericListU().get(0) instanceof BigDecimal);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_setters_with_hard_coded_nested_generic_type() {
        TypeWithGenericMethod<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericMethod<String, BigDecimal>>(){});
        assertNotNull(type.getTypedList());
        assertTrue(type.getTypedList().size() > 0);
        assertTrue(type.getTypedList().get(0) instanceof Double);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_doubly_nested_generic_type() {
        TypeWithGenericMethod<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericMethod<String, BigDecimal>>(){});
        assertNotNull(type.getGenericListOfListT());
        assertTrue(type.getGenericListOfListT().size() > 0);
        assertTrue(type.getGenericListOfListT().get(0).size() > 0);
        assertTrue(type.getGenericListOfListT().get(0).get(0) instanceof String);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_doubly_nested_hard_coded_generic_type() {
        TypeWithGenericMethod<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericMethod<String, BigDecimal>>(){});
        assertNotNull(type.getGenericTypesListOfList());
        assertTrue(type.getGenericTypesListOfList().size() > 0);
        assertTrue(type.getGenericTypesListOfList().get(0).size() > 0);
        assertTrue(type.getGenericTypesListOfList().get(0).get(0) instanceof Double);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_nested_types_with_multiple_generic_arguments() {
        TypeWithGenericMethod<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericMethod<String, BigDecimal>>(){});
        assertNotNull(type.getGenericListOfMap());
        assertTrue(type.getGenericListOfMap().size() > 0);
        assertTrue(type.getGenericListOfMap().get(0).size() > 0);
        Map.Entry<String, BigDecimal> outerMapFirstEntry = type.getGenericListOfMap().get(0).entrySet().iterator().next();
        assertTrue(outerMapFirstEntry.getKey() instanceof String);
        assertTrue(outerMapFirstEntry.getValue() instanceof BigDecimal);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void populates_fields_with_nested_types_with_multiple_partially_bounded_generic_arguments() {
        TypeWithGenericMethod<String, BigDecimal> type = fixture.create(new SpecimenType<TypeWithGenericMethod<String, BigDecimal>>() {});
        assertNotNull(type.getGenericPartiallyBound());
        assertTrue(type.getGenericPartiallyBound().size() > 0);
        Map.Entry<String, Map<Integer, BigDecimal>> outerMapFirstEntry = type.getGenericPartiallyBound().entrySet().iterator().next();
        Map.Entry<Integer, BigDecimal> innerMapFirstEntry = outerMapFirstEntry.getValue().entrySet().iterator().next();

        assertTrue(innerMapFirstEntry.getKey() instanceof Integer);
        assertTrue(innerMapFirstEntry.getValue() instanceof BigDecimal);
    }
}
