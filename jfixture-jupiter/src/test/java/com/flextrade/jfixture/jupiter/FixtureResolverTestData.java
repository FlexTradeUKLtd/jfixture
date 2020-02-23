package com.flextrade.jfixture.jupiter;

import java.util.List;
import java.util.Map;

public class FixtureResolverTestData {
    private String stringField;
    private int intField;
    private List<Map<String, Object>> listField;
    private Map<String, String> mapField;

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public List<Map<String, Object>> getListField() {
        return listField;
    }

    public void setListField(List<Map<String, Object>> listField) {
        this.listField = listField;
    }

    public Map<String, String> getMapField() {
        return mapField;
    }

    public void setMapField(Map<String, String> mapField) {
        this.mapField = mapField;
    }
}
