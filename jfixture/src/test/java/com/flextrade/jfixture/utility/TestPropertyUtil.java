package com.flextrade.jfixture.utility;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class TestPropertyUtil {

    @Test
    public void getting_member_name_returns_name_of_property_from_getter() throws NoSuchMethodException {
        Method property = TestClass.class.getMethod("getSymbol");
        String memberName = PropertyUtil.getMemberNameFromMethod(property);
        assertEquals("Symbol", memberName);
    }

    @Test
    public void getting_member_name_returns_name_of_property_from_setter() throws NoSuchMethodException {
        Method property = TestClass.class.getMethod("setSymbol", String.class);
        String memberName = PropertyUtil.getMemberNameFromMethod(property);
        assertEquals("Symbol", memberName);
    }

    @Test
    public void getting_member_name_containing_get_returns_name_of_property_from_getter() throws NoSuchMethodException {
        Method property = TestClass.class.getMethod("getCourgette");
        String memberName = PropertyUtil.getMemberNameFromMethod(property);
        assertEquals("Courgette", memberName);
    }

    @Test
    public void getting_member_name_containing_set_returns_name_of_property_from_setter() throws NoSuchMethodException {
        Method property = TestClass.class.getMethod("setCassette", String.class);
        String memberName = PropertyUtil.getMemberNameFromMethod(property);
        assertEquals("Cassette", memberName);
    }

    @Test
    public void getting_member_starting_with_is_returns_name_of_member() throws NoSuchMethodException {
        Method property = TestClass.class.getMethod("isAccepted");
        String memberName = PropertyUtil.getMemberNameFromMethod(property);
        assertEquals("Accepted", memberName);
    }

    public class TestClass {
        private String symbol;
        private String courgette;
        private String cassette;
        private Boolean isAccepted;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getCourgette() {
            return courgette;
        }

        public void setCourgette(String courgette) {
            this.courgette = courgette;
        }

        public String getCassette() {
            return cassette;
        }

        public void setCassette(String cassette) {
            this.cassette = cassette;
        }

        public Boolean isAccepted() {
            return isAccepted;
        }

        public void setAccepted(Boolean accepted) {
            isAccepted = accepted;
        }
    }
}
