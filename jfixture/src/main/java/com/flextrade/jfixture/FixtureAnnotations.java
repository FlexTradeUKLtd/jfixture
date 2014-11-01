package com.flextrade.jfixture;

import com.flextrade.jfixture.annotations.Fixture;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FixtureAnnotations {

    public static void initFixtures(Object obj) {
        initFixtures(obj, new JFixture());
    }

    public static void initFixtures(Object obj, JFixture fixture) {
        List<Field> fields = getAllFieldsInClassHierarchy(obj.getClass());
        initialiseAllFixtureFields(obj, fixture, fields);
    }

    private static void initialiseAllFixtureFields(Object obj, JFixture fixture, List<Field> fields) {
        for (Field field : fields) {

            if(isWritable(field) && isAnnotated(field)) {
                Object resolvedField = fixture.create(field);
                if (resolvedField instanceof NoSpecimen) continue;

                setFieldWithFixtureResult(obj, field, resolvedField);
            }
        }
    }

    private static boolean isWritable(Field field) {
        return !Modifier.isFinal(field.getModifiers());
    }

    private static boolean isAnnotated(Field field) {
        return field.getAnnotation(Fixture.class) != null;
    }

    private static void setFieldWithFixtureResult(Object obj, Field field, Object resolvedField) {
        try {
            field.setAccessible(true);
            field.set(obj, resolvedField);
        } catch (IllegalAccessException e) {
            System.err.println(
                    String.format("Unable to set value for field %s, please ensure it can be set", field.getName()));
            System.err.println(e.getMessage());
        }
    }

    private static List<Field> getAllFieldsInClassHierarchy(Class<?> startClass) {
        List<Field> currentClassFields = new ArrayList<Field>(Arrays.asList(startClass.getDeclaredFields()));
        Class<?> parentClass = startClass.getSuperclass();

        if (parentClass != null)  {
            List<Field> parentClassFields = getAllFieldsInClassHierarchy(parentClass);
            currentClassFields.addAll(parentClassFields);
        }

        return currentClassFields;
    }
}
