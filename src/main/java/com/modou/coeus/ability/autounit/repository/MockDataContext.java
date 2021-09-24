package com.modou.coeus.ability.autounit.repository;


import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.model.autounit.context.UnitMockContext;

public class MockDataContext {

    /**
     * 带参构造
     *
     * @param mockDataRepository
     */
    public MockDataContext(IMockDataRepository mockDataRepository) {
        this.mockDataRepository = mockDataRepository;
    }

    private IMockDataRepository mockDataRepository;

    /**
     * 得到mock数据
     * @param methodPair
     * @param unitMockContext
     * @return
     */
    public String mockTheData(UnitMethodPair methodPair, UnitMockContext unitMockContext){
        return mockDataRepository.mockData(methodPair, unitMockContext);
    }

}
