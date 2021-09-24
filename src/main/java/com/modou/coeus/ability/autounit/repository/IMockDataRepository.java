package com.modou.coeus.ability.autounit.repository;


import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.model.autounit.context.UnitMockContext;

public interface IMockDataRepository {

    /**
     * 数据mock
     * @param methodPair
     * @param unitMockContext
     * @return
     */
    String mockData(UnitMethodPair methodPair, UnitMockContext unitMockContext);

}
