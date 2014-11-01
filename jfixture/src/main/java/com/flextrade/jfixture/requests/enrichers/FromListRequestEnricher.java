package com.flextrade.jfixture.requests.enrichers;

import com.flextrade.jfixture.annotations.FromListOf;
import com.flextrade.jfixture.exceptions.ObjectCreationException;
import com.flextrade.jfixture.requests.ElementFromListRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class FromListRequestEnricher implements RequestEnricher {

    private final StringListBuilder stringListBuilder = new StringListBuilder();
    private final NumberListBuilder numberListBuilder = new NumberListBuilder();

    @Override
    public Object enrich(Object request, Annotation annotation) {
        if (!(annotation instanceof FromListOf)) {
            return null;
        }

        if (!(request instanceof Type)) {
            return null;
        }

        FromListOf fromList = (FromListOf) annotation;
        Type requestType = (Type) request;

        validateAnnotation(fromList);

        List<Object> objectList = populateList(fromList, requestType);
        return new ElementFromListRequest(objectList);
    }

    private List<Object> populateList(FromListOf fromList, Type requestType) {
        List<Object> objectList;
        if (fromList.strings().length > 0) {
            objectList = this.stringListBuilder.getList(fromList.strings());
        } else if (fromList.numbers().length > 0) {
            objectList = this.numberListBuilder.getList(fromList.numbers(), requestType);
        } else {
            objectList = Collections.emptyList();
        }
        return objectList;
    }

    private void validateAnnotation(FromListOf fromList) {
        if (fromList.numbers().length != 0 && fromList.strings().length != 0) {
            throw new ObjectCreationException("Invalid FromListOf annotation. Must specify only one list");
        }
    }
}
