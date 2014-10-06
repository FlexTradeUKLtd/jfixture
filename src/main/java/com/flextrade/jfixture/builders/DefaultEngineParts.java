package com.flextrade.jfixture.builders;

import com.flextrade.jfixture.DefaultConstructorQuery;
import com.flextrade.jfixture.DefaultFactoryMethodQuery;
import com.flextrade.jfixture.MultipleCount;
import com.flextrade.jfixture.SpecimenBuilder;
import com.flextrade.jfixture.requests.enrichers.CompositeRequestEnricher;
import com.flextrade.jfixture.requests.enrichers.FromListRequestEnricher;
import com.flextrade.jfixture.requests.enrichers.RangeRequestEnricher;
import com.flextrade.jfixture.specifications.AlwaysSpecification;
import com.flextrade.jfixture.specifications.TypeRequestSpecification;
import com.flextrade.jfixture.utility.RandomElementStrategy;
import com.flextrade.jfixture.utility.comparators.ConstructorParameterCountComparator;
import com.flextrade.jfixture.utility.comparators.MethodParameterCountComparator;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultEngineParts {
    public DefaultEngineParts() {

        this.add(new SeededStringBuilder());

        this.add(new StringGenerator());
        this.add(new NumberInRangeGenerator());
        this.add(new UuidGenerator());
        this.add(new DateGenerator());
        this.add(new CalendarBuilder());
        this.add(new SwitchingBooleanGenerator());
        this.add(new CharacterGenerator());
        this.add(new EnumBuilder());

        this.add(new MultipleSpecimenRelay(this.multipleCount));
        this.add(new ArrayRelay());
        this.add(new IterableRelay());
        this.add(new MapRelay());
        this.add(new KeyValuePairRelay());

        this.add(new MapBuilder());
        this.add(new QueueBuilder());
        this.add(new SetBuilder());
        this.add(new IterableBuilder());

        this.add(new GenericMethodBuilder());
        this.add(new GenericFieldRelay());
        this.add(new FieldRelay(
                new CompositeRequestEnricher(
                    new RangeRequestEnricher(),
                    new FromListRequestEnricher())));
        this.add(new GenericConstructorRelay());
        this.add(new FactoryMethodRelay());
        this.add(new ClassToConstructorRelay(
                new DefaultConstructorQuery(new ConstructorParameterCountComparator()),
                new AlwaysSpecification()));
        this.add(new ClassToFactoryMethodRelay(
                new DefaultFactoryMethodQuery(new MethodParameterCountComparator()),
                new TypeRequestSpecification()));

        this.add(new NumericRangeRelay());
        this.add(new ElementFromListRelay(
                new RandomElementStrategy()));
        this.add(new SeedIgnoringRelay());
    }

    public MultipleCount multipleCount() {
        return this.multipleCount;
    }

    public Collection<SpecimenBuilder> builders() {
        return this.builders;
    }

    private void add(SpecimenBuilder builder) {
        this.builders.add(builder);
    }

    private final MultipleCount multipleCount = new MultipleCount();
    private final ArrayList<SpecimenBuilder> builders = new ArrayList<SpecimenBuilder>();
}
