package com.flextrade.jfixture;

public interface BehaviourCommand {
    void execute(Object request, Object specimen, SpecimenContext specimenContext);
}
