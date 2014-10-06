package com.flextrade.jfixture.annotations;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRange {

    @Test
    public void Annotation_can_only_be_applied_to_fields() {
        Target target = Range.class.getAnnotation(Target.class);
        assertEquals(1, target.value().length);
        ElementType type = target.value()[0];
        assertTrue(type.equals(ElementType.FIELD));
    }

    @Test
    public void Annotation_is_retained_at_runtime() {
        Retention retention = Range.class.getAnnotation(Retention.class);
        RetentionPolicy retentionPolicy = retention.value();
        assertTrue(retentionPolicy.equals(RetentionPolicy.RUNTIME));
    }
}
