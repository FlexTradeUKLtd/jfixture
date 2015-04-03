package com.flextrade.jfixture.jodatime.customisation;

import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.behaviours.autoproperty.AutoPropertyBehaviour;
import com.flextrade.jfixture.customisation.Customisation;

public class JodaTimeCustomisation implements Customisation {
    @Override
    public void customise(JFixture fixture) {

        AutoPropertyBehaviour autoPropertyBehaviour = fixture.behaviours().find(AutoPropertyBehaviour.class);
        if (autoPropertyBehaviour != null) {
            autoPropertyBehaviour.specifications().add(new JodaTimeSpecification());
        }

        fixture.addBuilderToStartOfPipeline(new ReadableInstantRelay());
        fixture.addBuilderToStartOfPipeline(new ReadableIntervalRelay());
        fixture.addBuilderToStartOfPipeline(new ReadableDurationRelay());
        fixture.addBuilderToStartOfPipeline(new ReadablePartialRelay());
        fixture.addBuilderToStartOfPipeline(new ReadablePeriodRelay());

        fixture.addBuilderToStartOfPipeline(new PartialRelay());
        fixture.addBuilderToStartOfPipeline(new BasePeriodRelay());
        fixture.addBuilderToStartOfPipeline(new BaseSingleFieldPeriodRelay());
        fixture.addBuilderToStartOfPipeline(new BasePartialRelay());
        fixture.addBuilderToStartOfPipeline(new BaseLocalRelay());
        fixture.addBuilderToStartOfPipeline(new BaseDurationRelay());
        fixture.addBuilderToStartOfPipeline(new BaseIntervalRelay());
        fixture.addBuilderToStartOfPipeline(new BaseDateTimeRelay());

        fixture.addBuilderToStartOfPipeline(new DateTimeZoneBuilder());
        fixture.addBuilderToStartOfPipeline(new ChronologyBuilder());
        fixture.addBuilderToStartOfPipeline(new ChronologyBuilder());
    }
}
