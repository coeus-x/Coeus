package com.modou.coeus.utils;


import com.modou.coeus.model.autounit.UnitParamData;
import com.modou.coeus.model.autounit.context.UnitMockContext;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.UUID;


public class ReflectUtil {

    /**
     * 生成mock数据
     *
     * @param clazz
     * @return
     */
    public static String setMockDataByClass(Class clazz, String classVariableName, UnitMockContext unitMockContext) {
        StringBuilder builder = new StringBuilder();
        UnitParamData metaParamData = MetaDataUtil.getMetaParamData(clazz.getName());
        builder.append(RegexUtil.newLine()).append(RegexUtil.new4Tab()).append(RegexUtil.new3Tab());
        //生成变量名
        classVariableName = classVariableName + "Data" + RegexUtil.VARIABLE_SPLITTER + UUID.randomUUID();
        //变量名赋值
        builder.append(metaParamData.getClassName() + " " + classVariableName + " = " + metaParamData.getNewName() + ";").append(RegexUtil.newLine());
        //变量名存储到上下文备用
        unitMockContext.getInputParamVariables().add(classVariableName);
        //类需要进行属性set操作
        if (metaParamData.isAnalysisFields()) {
            for (Field field : clazz.getDeclaredFields()) {
                //不为final类型赋值
                if (!Modifier.isFinal(field.getModifiers())) {
                    builder.append(RegexUtil.new4Tab())
                            .append(RegexUtil.new3Tab())
                            .append(classVariableName + formatSetField(field))
                            .append(RegexUtil.newLine());
                }
            }
        }
        return builder.toString();
    }

    /**
     * 类似输出 .setName("test");
     *
     * @param field
     * @return
     */
    private static String formatSetField(Field field) {
        if (field.getType().isAssignableFrom(String.class)) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(\"testData\");";
        } else if (field.getType().isAssignableFrom(Double.class) || field.getType().getName().equals("double")) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(12d);";
        } else if (field.getType().isAssignableFrom(BigDecimal.class)) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(BigDecimal.valueOf(12));";
        } else if (field.getType().isAssignableFrom(Long.class) || field.getType().getName().equals("long")) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(12l);";
        } else if (field.getType().isAssignableFrom(Integer.class) || field.getType().getName().equals("int")) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(12);";
        } else if (field.getType().isAssignableFrom(Float.class) || field.getType().getName().equals("float")) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(12f);";
        } else if (field.getType().isAssignableFrom(Boolean.class) || field.getType().getName().equals("boolean")) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(true);";
        } else if (field.getType().isAssignableFrom(Short.class) || field.getType().getName().equals("short")) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(12);";
        } else if (field.getType().isAssignableFrom(Character.class) || field.getType().getName().equals("char")) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "('t');";
        } else if (field.getType().isAssignableFrom(Byte.class) || field.getType().getName().equals("byte")) {
            return ".set"
                    + (field.getName().charAt(0) + "").toUpperCase()
                    + field.getName().substring(1, field.getName().length())
                    + "(\"12\".getBytes()[0]);";
        }

        return ".set"
                + (field.getName().charAt(0) + "").toUpperCase()
                + field.getName().substring(1, field.getName().length())
                + "(null);";
    }
}
