package com.flextrade.jfixture.utility;

import java.util.List;

public interface ElementFromListStrategy {

    public <T> T get(List<T> list);
}
