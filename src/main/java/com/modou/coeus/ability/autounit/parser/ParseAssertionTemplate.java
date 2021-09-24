package com.modou.coeus.ability.autounit.parser;


import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.model.autounit.context.UnitMockContext;
import com.modou.coeus.utils.RegexUtil;

/**
 * @Description: 断言处理
 * @date 2021-08-04 18:32
 */
public class ParseAssertionTemplate implements IMethodBodyParse{

    @Override
    public String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        if(unitMockContext == null || unitMockContext.getReturnType() == null){
            return null;
        }

        StringBuilder builder = new StringBuilder();
        //非void类型添加assert断言
        if (!unitMockContext.getReturnType().equals("void")) {
            builder.append(RegexUtil.newLine()).append(RegexUtil.new4Tab()).append(RegexUtil.new3Tab());
            builder.append("assert returnResult != null;");
        }

        return builder.toString();
    }
}
