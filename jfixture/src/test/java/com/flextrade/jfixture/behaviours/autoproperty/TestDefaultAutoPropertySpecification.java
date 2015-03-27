package com.flextrade.jfixture.behaviours.autoproperty;

import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDefaultAutoPropertySpecification {

    private DefaultAutoPropertySpecification defaultSpecification;

    @Before
    public void initialise() {
        this.defaultSpecification = new DefaultAutoPropertySpecification();
    }

    @Test
    public void is_not_satisfied_by_non_class_requests() {
        assertFalse(this.defaultSpecification.isSatisfiedBy("string"));
        assertFalse(this.defaultSpecification.isSatisfiedBy(1));
        assertFalse(this.defaultSpecification.isSatisfiedBy(String.class.getConstructors()[0]));
    }

    @Test
    public void is_not_satisfied_by_date_class() {
        assertFalse(this.defaultSpecification.isSatisfiedBy(SpecimenType.of(Date.class)));
    }

    @Test
    public void is_not_satisfied_by_calendar_class() {
        assertFalse(this.defaultSpecification.isSatisfiedBy(SpecimenType.of(Date.class)));
    }

    @Test
    public void is_satisfied_by_class_requests() {
        assertTrue(this.defaultSpecification.isSatisfiedBy(SpecimenType.of(String.class)));
        assertTrue(this.defaultSpecification.isSatisfiedBy(SpecimenType.of(Integer.class)));
        assertTrue(this.defaultSpecification.isSatisfiedBy(SpecimenType.of(List.class)));
    }
}
