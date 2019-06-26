package com.flextrade.jfixture.annotations;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.Assert.assertEquals;

public class TestFromListOf {

    @Test
    public void Annotation_can_only_be_applied_to_fields() {
        Target target = FromListOf.class.getAnnotation(Target.class);
        assertEquals(1, target.value().length);
        ElementType type = target.value()[0];
        assertEquals(ElementType.FIELD, type);
    }

    @Test
    public void Annotation_is_retained_at_runtime() {
        Retention retention = FromListOf.class.getAnnotation(Retention.class);
        RetentionPolicy retentionPolicy = retention.value();
        assertEquals(RetentionPolicy.RUNTIME, retentionPolicy);
    }
}
