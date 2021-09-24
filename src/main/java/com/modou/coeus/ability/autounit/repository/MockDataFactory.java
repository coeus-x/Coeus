package com.modou.coeus.ability.autounit.repository;


import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.model.autounit.context.UnitMockContext;

/**
 * 数据工厂
 */
public class MockDataFactory {

    /**
     * mock本地数据
     *
     * @param methodPair
     * @param unitMockContext
     * @return
     */
    public static String mockLocalData(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        IMockDataRepository mockDataRepository = new MockDataLocalRepository();
        MockDataContext mockDataContext = new MockDataContext(mockDataRepository);
        return mockDataContext.mockTheData(methodPair, unitMockContext);
    }


}
