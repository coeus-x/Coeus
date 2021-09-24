package com.modou.coeus.utils;


import com.modou.coeus.model.autounit.UnitParamData;

public class MetaDataUtil {

    public static UnitParamData getMetaParamData(String className) {
        UnitParamData unitParamData = null;
        if (className.equals("java.lang.String")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("String");
            unitParamData.setNewName("\"testData\"");
            unitParamData.setMockName("Mockito.anyString()");
        } else if (className.equals("int")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("int");
            unitParamData.setNewName("0");
            unitParamData.setMockName("Mockito.anyInt()");
        } else if (className.equals("java.lang.Integer")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Integer");
            unitParamData.setNewName("0");
            unitParamData.setMockName("Mockito.anyInt()");
        } else if (className.equals("long")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("long");
            unitParamData.setNewName("0l");
            unitParamData.setMockName("Mockito.anyLong()");
        } else if (className.equals("java.lang.Long")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Long");
            unitParamData.setNewName("0l");
            unitParamData.setMockName("Mockito.anyLong()");
        } else if (className.equals("float")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("float");
            unitParamData.setNewName("0.0");
            unitParamData.setMockName("Mockito.anyFloat()");
        } else if (className.equals("java.lang.Float")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Float");
            unitParamData.setNewName("0.0");
            unitParamData.setMockName("Mockito.anyFloat()");
        } else if (className.equals("double")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("double");
            unitParamData.setNewName("0.0");
            unitParamData.setMockName("Mockito.anyDouble()");
        } else if (className.equals("java.lang.Double")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Double");
            unitParamData.setNewName("0.0");
            unitParamData.setMockName("Mockito.anyDouble()");
        } else if (className.equals("byte")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("byte");
            unitParamData.setNewName("0");
            unitParamData.setMockName("Mockito.anyByte()");
        } else if (className.equals("java.lang.Byte")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Byte");
            unitParamData.setNewName("0");
            unitParamData.setMockName("Mockito.anyByte()");
        } else if (className.equals("short")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("short");
            unitParamData.setNewName("0");
            unitParamData.setMockName("Mockito.anyShort()");
        } else if (className.equals("java.lang.Short")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Short");
            unitParamData.setNewName("0");
            unitParamData.setMockName("Mockito.anyShort()");
        } else if (className.equals("boolean")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("boolean");
            unitParamData.setNewName("true");
            unitParamData.setMockName("Mockito.anyBoolean()");
        } else if (className.equals("java.lang.Boolean")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Boolean");
            unitParamData.setNewName("true");
            unitParamData.setMockName("Mockito.anyBoolean()");
        } else if (className.equals("char")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("char");
            unitParamData.setNewName("a");
            unitParamData.setMockName("Mockito.anyChar()");
        } else if (className.equals("java.lang.Character")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Character");
            unitParamData.setNewName("new Character('a')");
            unitParamData.setMockName("Mockito.anyChar()");
        } else if (className.equals("java.util.List")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("List");
            unitParamData.setNewName("new ArrayList()");
            unitParamData.setMockName("Mockito.anyList()");
        } else if (className.equals("java.util.Map")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Map");
            unitParamData.setNewName("new HashMap()");
            unitParamData.setMockName("Mockito.anyMap()");
        } else if (className.equals("java.util.Set")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Set");
            unitParamData.setNewName("new HashSet()");
            unitParamData.setMockName("Mockito.anySet()");
        } else if (className.equals("java.util.Collection")) {
            unitParamData = new UnitParamData();
            unitParamData.setClassName("Collection");
            unitParamData.setNewName("new ArrayList()");
            unitParamData.setMockName("Mockito.anyCollection()");
        } else {
            String name = className.substring(className.lastIndexOf(".") + 1, className.length());
            unitParamData = new UnitParamData();
            unitParamData.setClassName(name);
            unitParamData.setNewName("new " + name + "()");
            unitParamData.setAnalysisFields(true);
        }
        return unitParamData;
    }

    /**
     * 基本类型转换，诸如 int 转换为 Integer
     *
     * @param metaParamData
     * @return
     */
    public static String convertMetaParamData(String metaParamData) {
        if (metaParamData == null) {
            return null;
        }

        if (metaParamData.equals("int")) {
            return "Integer";
        } else if (metaParamData.equals("long")) {
            return "Long";
        } else if (metaParamData.equals("boolean")) {
            return "Boolean";
        } else if (metaParamData.equals("byte")) {
            return "Byte";
        } else if (metaParamData.equals("float")) {
            return "Float";
        } else if (metaParamData.equals("double")) {
            return "Double";
        } else if (metaParamData.equals("short")) {
            return "Short";
        } else if (metaParamData.equals("char")) {
            return "Char";
        }

        return metaParamData;
    }
}
