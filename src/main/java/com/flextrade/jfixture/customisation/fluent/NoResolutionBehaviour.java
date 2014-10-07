package com.flextrade.jfixture.customisation.fluent;

import com.flextrade.jfixture.CustomisationContainer;
import com.flextrade.jfixture.FluentCustomisation;
import com.flextrade.jfixture.customisation.IgnoreNoResolutionCustomisation;
import com.flextrade.jfixture.customisation.ThrowOnNoResolutionCustomisation;

public class NoResolutionBehaviour {

    private final CustomisationContainer customisationContainer;
    private final FluentCustomisation fluentCustomisation;

    public NoResolutionBehaviour(CustomisationContainer customisationContainer, FluentCustomisation fluentCustomisation) {
        this.customisationContainer = customisationContainer;
        this.fluentCustomisation = fluentCustomisation;
    }

    public FluentCustomisation throwException() {
        this.customisationContainer.customise(new ThrowOnNoResolutionCustomisation());
        return this.fluentCustomisation;
    }

    public FluentCustomisation omitSpecimen() {
        this.customisationContainer.customise(new IgnoreNoResolutionCustomisation());
        return this.fluentCustomisation;
    }
}