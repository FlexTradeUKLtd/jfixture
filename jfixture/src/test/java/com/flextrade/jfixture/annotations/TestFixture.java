package com.flextrade.jfixture.annotations;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.hamcrest.Matchers.arrayContaining;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestFixture {

    @Test
    public void Annotation_can_only_be_applied_to_fields() {
        Target target = Fixture.class.getAnnotation(Target.class);
        assertEquals(2, target.value().length);
        ElementType[] types = target.value();
        assertThat(types, arrayContaining(ElementType.FIELD, ElementType.PARAMETER));
    }

    @Test
    public void Annotation_is_retained_at_runtime() {
        Retention retention = Fixture.class.getAnnotation(Retention.class);
        RetentionPolicy retentionPolicy = retention.value();
        assertEquals(RetentionPolicy.RUNTIME, retentionPolicy);
    }
}
