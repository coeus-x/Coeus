package com.modou.coeus.ability.autounit.parser;


import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.model.autounit.context.UnitMockContext;
import com.modou.coeus.utils.MetaDataUtil;
import com.modou.coeus.utils.RegexUtil;

public class ParseOutputParamTemplate implements IMethodBodyParse {

    @Override
    public String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(RegexUtil.newLine()).append(RegexUtil.new4Tab()).append(RegexUtil.new3Tab());

        //获取返回值
        String returnType = RegexUtil.getClassName(methodPair.getMethod().getReturnType().getName());
        if (!returnType.equals("void")) {
            stringBuilder.append(MetaDataUtil.convertMetaParamData(returnType) + " returnResult = ");
        }

        //将返回值类型暂存上下文
        unitMockContext.setReturnType(returnType);

        //将返回类型的package导入
        if(methodPair.getMethod().getReturnType().getName().contains(".")) {
            unitMockContext.getUnitImportPackageList().add(methodPair.getMethod().getReturnType().getName());
        }

        return stringBuilder.toString();
    }
}
