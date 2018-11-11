package com.flextrade.jfixture.utility;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSpecimenType {

    @Test(expected = IllegalArgumentException.class)
    public void throws_exception_if_created_with_no_generic_arguments() {
        new SpecimenType(){};
    }

    @Test
    public void non_generic_class_type_argument_has_correct_raw_type() {
        assertEquals(String.class, new SpecimenType<String>(){}.getRawType());
    }

    @Test
    public void non_generic_class_type_argument_has_no_generic_types() {
        assertEquals(0, new SpecimenType<String>(){}.getGenericTypeArguments().getLength());
    }

    @Test
    public void generic_class_type_argument_has_correct_raw_type() {
        assertEquals(List.class, new SpecimenType<List<String>>(){}.getRawType());
    }

    @Test
    public void generic_class_type_argument_has_correct_generic_type() {
        assertEquals(1, new SpecimenType<List<String>>(){}.getGenericTypeArguments().getLength());
        assertEquals(String.class, new SpecimenType<List<String>>(){}.getGenericTypeArguments().get(0).getType().getRawType());
    }

    @Test
    public void generic_class_type_argument_has_correct_generic_type_name() {
        assertEquals("E", new SpecimenType<List<String>>(){}.getGenericTypeArguments().get(0).getName());
    }

    @Test
    public void non_generic_array_type_argument_has_correct_raw_type() {
        assertEquals(String[].class, new SpecimenType<String[]>(){}.getRawType());
    }

    @Test
    public void non_generic_array_type_argument_has_no_generic_types() {
        assertEquals(0, new SpecimenType<String[]>(){}.getGenericTypeArguments().getLength());
    }

    @Test
    public void primitive_void_raw_type_is_supported() {
       assertEquals(Void.class, SpecimenType.of(void.class).getRawType());
       assertEquals(0, SpecimenType.of(void.class).getGenericTypeArguments().getLength());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void wildcard_types_are_not_supported() {
        new SpecimenType<List<? extends Number>>(){};
    }
}
