package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;

public class SubTypeCustomisation <T, U extends T>  implements Customisation {

    private final Class<T> baseClass;
    private final Class<U> subClass;

    public SubTypeCustomisation(Class<T> baseClass, Class<U> subClass) {
        this.baseClass = baseClass;
        this.subClass = subClass;
    }

    @Override
    public void customise(JFixture fixture) {
        fixture.addBuilderToStartOfPipeline(new SubTypeRelay(this.baseClass, this.subClass));
    }
}
