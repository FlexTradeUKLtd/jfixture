package com.flextrade.jfixture.behaviours.autoproperty;

import com.flextrade.jfixture.BehaviourCommand;
import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.FieldRequest;
import com.flextrade.jfixture.requests.MethodRequest;
import com.flextrade.jfixture.utility.PropertyUtil;
import com.flextrade.jfixture.utility.SpecimenType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

class AutoPropertyPopulater implements BehaviourCommand {

    public void execute(Object request, Object specimen, SpecimenContext specimenContext) {
        if (specimen == null) return;
        if (specimenContext == null) throw new IllegalArgumentException("specimenContext  must not be null");

        this.populateSetters(request, specimen, specimenContext);
        this.populateFields(request, specimen, specimenContext);
    }

    private void populateSetters(Object request, Object specimen, SpecimenContext specimenContext) {
        Class specimenClass = specimen.getClass();
        Method[] methods = specimenClass.getMethods();

        for (Method method : methods) {
            if (PropertyUtil.isMethodASetterProperty(method, specimenClass)) {
                MethodRequest methodRequest = getGenericMethodRequest(request, method);
                Object propertySpecimen = specimenContext.resolve(methodRequest);

                if (!(propertySpecimen instanceof NoSpecimen)) {
                    trySetProperty(specimen, method, propertySpecimen);
                }
            }
        }
    }

    private void populateFields(Object request, Object specimen, SpecimenContext specimenContext) {
        Class specimenClass = specimen.getClass();
        Field[] fields = specimenClass.getFields();

        for (Field field : fields) {
            if (isSettable(field)) {
                FieldRequest fieldRequest = getGenericFieldRequest(request, field);
                Object propertySpecimen = specimenContext.resolve(fieldRequest);

                if (!(propertySpecimen instanceof NoSpecimen)) {
                    trySetField(specimen, field, propertySpecimen);
                }
            }
        }
    }

    private void trySetField(Object specimen, Field field, Object propertySpecimen) {
        try {
            field.set(specimen, propertySpecimen);
        } catch (Exception e) {
            throw new ObjectCreationException(String.format("Error setting field %s", field.getName()), e);
        }
    }

    private void trySetProperty(Object specimen, Method method, Object propertySpecimen) {
        try {
            method.invoke(specimen, propertySpecimen);
        } catch (Exception e) {
            throw new ObjectCreationException(String.format("Error setting property %s", method.getName()), e);
        }
    }

    private boolean isSettable(Field field) {
        int modifiers = field.getModifiers();
        return !Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers);
    }

    private MethodRequest getGenericMethodRequest(final Object request, Method method) {

        if(!(request instanceof Type)) return null;
        SpecimenType specimenType = SpecimenType.of((Type) request);
        return new MethodRequest(method, specimenType);
    }

    private FieldRequest getGenericFieldRequest(Object request, Field field) {

        if(!(request instanceof Type)) return null;
        SpecimenType specimenType = SpecimenType.of((Type) request);
        return new FieldRequest(field, specimenType);
    }
}