package com.flextrade.jfixture.behaviours.noresolution;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

class FailedRequestEstimator {

    public String getEstimatedFailureReason(Object request) {
        if (request == null) {
            return "Request is null";
        }

        if (!(request instanceof Class)) {
            return "Request for " + request.toString() + " failure reason unknown";
        }

        Class requestClass = (Class) request;
        if (Modifier.isInterface(requestClass.getModifiers())) {
            return "Request for " + request.toString() + " is interface type. Ensure this interface type can be resolved to a concrete type";
        }

        if (Modifier.isAbstract(requestClass.getModifiers())) {
            return "Request for " + request.toString() + " is abstract type. Ensure this type can be resolved to a concrete type";
        }

        String constructorFailureReason = tryGetConstructorFailureReasons(requestClass);
        if(constructorFailureReason != null) return constructorFailureReason;

        return "Request for " + request.toString() + " failure reason unknown";
    }

    private String tryGetConstructorFailureReasons(Class request) {
        Constructor[] constructors = request.getConstructors();
        for(Constructor ctor : constructors) {
            if(Modifier.isPublic(ctor.getModifiers()))
                return null;
        }

        return "Request for " + request.toString() + " does not contain a public constructor";
    }
}
