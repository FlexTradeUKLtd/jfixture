package com.flextrade.jfixture.mockito.customisation;

import com.flextrade.jfixture.NoSpecimen;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.SpecimenContext;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.mockito.utility.PropertyUtil;
import com.flextrade.jfixture.utility.SpecimenType;

import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.mockito.Mockito.when;

class MockitoRelay implements SpecimenBuilder {

    @Override
    public Object create(Object request, SpecimenContext specimenContext) {
        
        if (!(request instanceof SpecimenType)) {
            return new NoSpecimen();
        }
        
        SpecimenType<?> specimenType = (SpecimenType<?>)request;
        Class<?> requestClass = specimenType.getRawType();

        if (!requestClass.isInterface() && !Modifier.isAbstract(requestClass.getModifiers())) {
            return new NoSpecimen();
        }

        Object mockResult = Mockito.mock(requestClass);
        setupStubPropertiesOnMock(mockResult, requestClass, specimenContext);

        return mockResult;
    }

    private void setupStubPropertiesOnMock(Object mock, Class<?> requestClass, SpecimenContext specimenContext) {
        Method[] methods = requestClass.getMethods();
        for (Method method : methods) {

            Boolean isAGetterProperty = PropertyUtil.isMethodAGetterProperty(method);
            if (!isAGetterProperty) continue;

            Object propertySpecimen = specimenContext.resolve(method.getGenericReturnType());
            if (propertySpecimen instanceof NoSpecimen) continue;

            try {
                when(method.invoke(mock)).thenReturn(propertySpecimen);
            } catch (Exception e) {
                throw new ObjectCreationException(String.format("Unable to create mock of %s", requestClass), e);
            }
        }
    }
}
