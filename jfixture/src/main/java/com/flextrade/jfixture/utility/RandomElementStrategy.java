package com.flextrade.jfixture.utility;

import java.util.List;
import java.util.Random;

public class RandomElementStrategy implements ElementFromListStrategy {

    private static final Random random = new Random();

    @Override
    public <T> T get(List<T> list) {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
