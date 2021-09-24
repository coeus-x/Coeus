package com.modou.coeus.ability.autounit.parser;


import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.model.autounit.context.UnitMockContext;

public class ParseInputParamsTemplate implements IMethodBodyParse {

    @Override
    public String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        StringBuilder builder = new StringBuilder();
        int variableLength = unitMockContext.getInputParamVariables().size();
        if (variableLength > 0) {
            builder.append(unitMockContext.getUnitInjectModel().getClassName() + "." + methodPair.getCtMethod().getName() + "(");
            for (int i = 0; i < variableLength; i++) {
                String variableName = unitMockContext.getInputParamVariables().get(i);
                builder.append(variableName);
                if (i < variableLength - 1) {
                    builder.append(",");
                }
            }
            builder.append(");");
        }
        //无参
        else {
            builder.append(unitMockContext.getUnitInjectModel().getClassName() + "." + methodPair.getCtMethod().getName() + "();");
        }

        //返回之前清空
        unitMockContext.getInputParamVariables().clear();

        return builder.toString();
    }
}
