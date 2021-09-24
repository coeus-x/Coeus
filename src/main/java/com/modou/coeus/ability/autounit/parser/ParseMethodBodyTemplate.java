package com.modou.coeus.ability.autounit.parser;


import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.ability.autounit.repository.MockDataFactory;
import com.modou.coeus.model.autounit.UnitParamData;
import com.modou.coeus.model.autounit.context.UnitMockContext;
import com.modou.coeus.model.autounit.context.UnitMockModel;
import com.modou.coeus.utils.MetaDataUtil;
import com.modou.coeus.utils.RegexUtil;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;


@Slf4j
public class ParseMethodBodyTemplate implements IMethodBodyParse {

    @Override
    public String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        StringBuilder builder = new StringBuilder();
        //实例打桩
        builder.append(createStubs(methodPair, unitMockContext));
        //数据Mock
        builder.append(MockDataFactory.mockLocalData(methodPair, unitMockContext));
        builder.append(RegexUtil.newLine());
        return builder.toString();
    }

    /**
     * 打桩
     *
     * @param methodPair
     * @param unitMockContext
     * @return
     */
    private String createStubs(UnitMethodPair methodPair, UnitMockContext unitMockContext) {
        StringBuilder builder = new StringBuilder();
        //走查方法体，对@Mock中的实体进行打桩
        Set<String> mockedMethods = new HashSet<>();
        for (UnitMockModel unitMockModel : unitMockContext.getUnitMockModelList()) {
            Set<String> set = findMockedMethods(methodPair.getCtMethod(), new HashSet<>(), 0, unitMockModel);
            mockedMethods.addAll(set);
        }
        for (String mockedMethod : mockedMethods) {
            builder.append(mockedMethod).append(RegexUtil.newLine()).append(RegexUtil.new4Tab()).append(RegexUtil.new3Tab());
        }
        builder.append(RegexUtil.newLine());
        return builder.toString();
    }

    /**
     * 遍历method，直至找到能力类
     *
     * @param ctMethod
     * @param deep
     */
    private static Set<String> findMockedMethods(CtMethod ctMethod, Set<String> set, int deep, UnitMockModel unitMockModel) {
        try {
            Integer MAX_DEEP = 10;
            ExprEditor exprEditor = new ExprEditor() {
                public void edit(MethodCall m) {
                    try {
                        String methodCallName = m.getClassName();
                        Class refClass = null;
                        try {
                            refClass = Class.forName(methodCallName);
                            if (!refClass.getName().startsWith("com.jd") && !refClass.getName().startsWith("org.tiny")) {
                                return;
                            }
                        }
                        catch (ClassNotFoundException e) { }
                        catch (ExceptionInInitializerError e) { }
                        catch (NoClassDefFoundError e){ }

                        //找到继承对象
                        if (refClass != null && unitMockModel.getClazz().isAssignableFrom(refClass)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            int paramCount = m.getMethod().getParameterTypes().length;
                            CtClass returnType = m.getMethod().getReturnType();
                            //void返回不打桩
                            if (returnType.getName().equals("void")) {
                                return;
                            }
                            stringBuilder.append("when(" + unitMockModel.getClassName() + "." + m.getMethodName() + "(");
                            for (int i = 0; i < paramCount; i++) {
                                CtClass parameterType = m.getMethod().getParameterTypes()[i];
                                UnitParamData unitParamData = MetaDataUtil.getMetaParamData(parameterType.getName());
                                if (!unitParamData.isAnalysisFields()) {
                                    //stringBuilder.append("eq("+unitParamData.getNewName()+")");
                                    stringBuilder.append(unitParamData.getMockName());
                                } else {
                                    stringBuilder.append("Mockito.any()");
                                }
                                if (i < paramCount - 1) {
                                    stringBuilder.append(",");
                                }
                            }
                            UnitParamData unitParamData = MetaDataUtil.getMetaParamData(returnType.getName());
                            if (unitParamData != null) {
                                stringBuilder.append(")).thenReturn(" + unitParamData.getNewName() + ");");
                            } else {
                                stringBuilder.append(")).thenReturn(Mockito.any());");
                            }
                            set.add(stringBuilder.toString());
                        }
                        //递归查找
                        else {
                            if (deep > MAX_DEEP) {
                                return;
                            }
                            findMockedMethods(m.getMethod(), set, deep + 1, unitMockModel);
                        }
                    } catch (Exception e) {
                        log.error("ParseMethodBodyTemplate.findMockedMethods.loop error. ", e);
                    }
                }
            };
            ctMethod.instrument(exprEditor);
        } catch (Exception e) {
            log.error("ParseMethodBodyTemplate.findMockedMethods error", e);
        }

        return set;
    }
}