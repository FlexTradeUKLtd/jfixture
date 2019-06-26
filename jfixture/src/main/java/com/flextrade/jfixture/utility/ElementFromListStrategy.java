package com.flextrade.jfixture.utility;

import java.util.List;

public interface ElementFromListStrategy {

    <T> T get(List<T> list);
}
