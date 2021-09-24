package com.modou.coeus.ability.autounit.strategy;


import com.modou.coeus.ability.autounit.UnitClassMethod;
import com.modou.coeus.model.autounit.UnitStrategyContent;
import com.modou.coeus.model.autounit.context.UnitMockContext;

public interface IUnitBuildStrategy {

    UnitStrategyContent build(UnitClassMethod unitClassMethod, UnitMockContext mockContext);

}
