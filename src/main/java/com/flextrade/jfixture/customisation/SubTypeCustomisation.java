package com.flextrade.jfixture.customisation;

import com.flextrade.jfixture.JFixture;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubTypeCustomisation <T, U extends T>  implements Customisation {

    private final Class<T> baseClass;
    private final Class<U> subClass;

    @Override
    public void customise(JFixture fixture) {
        fixture.addBuilderToStartOfPipeline(new SubTypeRelay(this.baseClass, this.subClass));
    }
}
