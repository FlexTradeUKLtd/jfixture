package com.flextrade.jfixture.requests.enrichers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StringListBuilder {
    public List<Object> getList(String[] strings) {
        List<Object> objectList = new ArrayList<Object>();
        Collections.addAll(objectList, strings);
        return objectList;
    }
}
