package com.flextrade.jfixture.customisation.fluent;

import com.flextrade.jfixture.CustomisationContainer;
import com.flextrade.jfixture.FluentCustomisation;
import com.flextrade.jfixture.customisation.OmitSpecimenRecursionCustomisation;
import com.flextrade.jfixture.customisation.ThrowingRecursionCustomisation;

public class RecursionBehaviour {

    private final CustomisationContainer customisationContainer;
    private final FluentCustomisation fluentCustomisation;

    public RecursionBehaviour(CustomisationContainer customisationContainer, FluentCustomisation fluentCustomisation) {
        this.customisationContainer = customisationContainer;
        this.fluentCustomisation = fluentCustomisation;
    }

    public FluentCustomisation throwException() {
        this.customisationContainer.customise(new ThrowingRecursionCustomisation());
        return this.fluentCustomisation;
    }

    public FluentCustomisation omitSpecimen() {
        this.customisationContainer.customise(new OmitSpecimenRecursionCustomisation());
        return this.fluentCustomisation;
    }
}