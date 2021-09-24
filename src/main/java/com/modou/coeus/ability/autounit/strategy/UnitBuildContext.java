package com.modou.coeus.ability.autounit.strategy;


import com.modou.coeus.ability.autounit.UnitClassMethod;
import com.modou.coeus.model.autounit.UnitStrategyContent;
import com.modou.coeus.model.autounit.context.UnitMockContext;

public class UnitBuildContext {

    /**
     * 带参构造
     *
     * @param unitBuildStrategy
     */
    public UnitBuildContext(IUnitBuildStrategy unitBuildStrategy) {
        this.unitBuildStrategy = unitBuildStrategy;
    }

    //构建策略
    private IUnitBuildStrategy unitBuildStrategy;

    /**
     * 构建具体部件
     */
    public UnitStrategyContent build(UnitClassMethod unitClassMethod, UnitMockContext unitMockContext) {
        return unitBuildStrategy.build(unitClassMethod, unitMockContext);
    }

}
