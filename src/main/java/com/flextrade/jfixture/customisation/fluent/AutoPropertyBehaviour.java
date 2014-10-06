package com.flextrade.jfixture.customisation.fluent;

import com.flextrade.jfixture.CustomisationContainer;
import com.flextrade.jfixture.FluentCustomisation;
import com.flextrade.jfixture.customisation.AutoPropertyCustomisation;
import com.flextrade.jfixture.customisation.OmitAutoPropertyCustomisation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AutoPropertyBehaviour {

    private final CustomisationContainer customisationContainer;
    private final FluentCustomisation fluentCustomisation;

    public FluentCustomisation omitSpecimen() {
        this.customisationContainer.customise(new OmitAutoPropertyCustomisation());
        return this.fluentCustomisation;
    }

    public FluentCustomisation populateSpecimen() {
        this.customisationContainer.customise(new AutoPropertyCustomisation());
        return this.fluentCustomisation;
    }
}
