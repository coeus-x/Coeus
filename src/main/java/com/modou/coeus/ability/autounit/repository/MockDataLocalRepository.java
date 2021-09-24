package com.modou.coeus.ability.autounit.repository;


import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.model.autounit.context.UnitMockContext;
import com.modou.coeus.utils.ReflectUtil;
import com.modou.coeus.utils.RegexUtil;

public class MockDataLocalRepository implements IMockDataRepository {

    @Override
    public String mockData(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        StringBuilder builder = new StringBuilder();
        builder.append(RegexUtil.new4Tab()).append(RegexUtil.new3Tab());
        for (Class<?> aClass : methodPair.getMethod().getParameterTypes()) {
            //带有.注解的，将其添加到import package列表中
            if (aClass.getName().contains(".")) {
                unitMockContext.getUnitImportPackageList().add(aClass.getName());
            }
            //获取变量名
            String variableName = RegexUtil.getClassVariableName(aClass.getName());
            //设置本地mock数据
            builder.append(ReflectUtil.setMockDataByClass(aClass, variableName, unitMockContext));
        }
        return builder.toString();
    }
}
