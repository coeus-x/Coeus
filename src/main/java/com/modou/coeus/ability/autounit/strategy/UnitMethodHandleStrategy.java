package com.modou.coeus.ability.autounit.strategy;


import com.modou.coeus.ability.autounit.UnitClassMethod;
import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.ability.autounit.parser.ParseAssertionTemplate;
import com.modou.coeus.ability.autounit.parser.ParseInputParamsTemplate;
import com.modou.coeus.ability.autounit.parser.ParseMethodBodyTemplate;
import com.modou.coeus.ability.autounit.parser.ParseOutputParamTemplate;
import com.modou.coeus.model.autounit.UnitClassType;
import com.modou.coeus.model.autounit.UnitStrategyContent;
import com.modou.coeus.model.autounit.context.UnitMockContext;
import com.modou.coeus.utils.FileOpsUtil;
import com.modou.coeus.utils.RegexUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UnitMethodHandleStrategy implements IUnitBuildStrategy {

    @Override
    public UnitStrategyContent build(UnitClassMethod unitClassMethod, UnitMockContext unitMockContext) {

        UnitStrategyContent unitStrategyContent = new UnitStrategyContent();

        //空对象则直接返回
        if (unitClassMethod == null || unitClassMethod.getMethodPairs() == null || unitClassMethod.getMethodPairs().size() == 0) {
            return unitStrategyContent;
        }

        //遍历集合，生成单测方法
        for (UnitMethodPair methodPair : unitClassMethod.getMethodPairs()) {
            String methodName = "when_" + methodPair.getCtMethod().getName() + "_then_return_success";
            String methodBody = getMehtodTemplateContent();
            //替换掉方法名称
            methodBody = methodBody.replace(UnitClassType.test_method_name.getExpr(), methodName);
            //替换掉方法内容
            methodBody = methodBody.replace(UnitClassType.test_method_body.getExpr(), handleMethodTemplateContent(methodPair, unitMockContext));
            //不存在则添加
            if (!unitStrategyContent.getContent().containsKey(UnitClassType.method_body)) {
                unitStrategyContent.getContent().put(UnitClassType.method_body, new StringBuilder().append(methodBody));
            }
            //存在则追加
            else {
                unitStrategyContent.getContent().get(UnitClassType.method_body)
                        .append(RegexUtil.newLine())
                        .append(RegexUtil.new4Tab())
                        .append(methodBody);
            }
        }
        return unitStrategyContent;
    }

    /**
     * 获取方法模板内容
     *
     * @return
     */
    private String getMehtodTemplateContent() {
        String methodTemplateContent = FileOpsUtil.readTemplate("template/MethodTemplate");
        return methodTemplateContent;
    }

    /**
     * 处理方法体
     *
     * @param methodPair
     * @param unitMockContext
     * @return
     */
    private String handleMethodTemplateContent(UnitMethodPair methodPair, UnitMockContext unitMockContext) {

        StringBuilder builder = new StringBuilder();

        //方法体处理
        String methodBody = new ParseMethodBodyTemplate().parse(methodPair, unitMockContext);
        builder.append(paramVariableHandle(methodBody, unitMockContext));

        //返参处理
        builder.append(new ParseOutputParamTemplate().parse(methodPair, unitMockContext));

        //入参处理
        builder.append(new ParseInputParamsTemplate().parse(methodPair, unitMockContext));

        //断言处理
        builder.append(new ParseAssertionTemplate().parse(methodPair, unitMockContext));

        //结果返回
        return builder.toString();
    }

    /**
     * 方法体中的变量名修正
     * @param methodBody
     * @param unitMockContext
     */
    private String paramVariableHandle(String methodBody, UnitMockContext unitMockContext) {
        String methodBodyFixed = methodBody;
        if (unitMockContext.getInputParamVariables().size() > 0) {
            int len = unitMockContext.getInputParamVariables().size();
            for (int i = 0; i < len; i++) {
                String variableName = unitMockContext.getInputParamVariables().get(i);
                String variableNameAppendix = RegexUtil.getVariableNameAppendix(variableName);
                if(variableNameAppendix != null) {
                    methodBodyFixed = methodBodyFixed.replace(variableNameAppendix, i + "");
                }
                unitMockContext.getInputParamVariables().set(i, variableName.replace(variableNameAppendix, i+""));
            }
        }
        return methodBodyFixed;
    }
}