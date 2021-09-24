package com.modou.coeus.utils;

import java.io.File;


public class RegexUtil {

    public static final String VARIABLE_SPLITTER = "##&&##";

    /**
     * 根据类全路径名称获取类名
     *
     * @param classFullName
     */
    public static String getClassName(String classFullName) {
        return getClassName(classFullName, ".");
    }

    /**
     * 根据类全路径名称获取类名
     *
     * @param classFullName
     */
    public static String getClassVariableName(String classFullName) {
        String className = getClassName(classFullName);
        return new String(className.charAt(0) + "").toLowerCase() + className.substring(1, className.length());
    }

    /**
     * 根据类全路径名称获取类名
     *
     * @param classFullName
     */
    public static String getFormatedClassName(String classFullName) {
        return "Test" + getClassName(classFullName);
    }

    /**
     * 根据类全路径名称获取类名
     *
     * @param classFullName
     */
    public static String getClassName(String classFullName, String splitter) {
        return classFullName.substring(classFullName.lastIndexOf(splitter) + 1, classFullName.length());
    }

    /**
     * 根据类全路径名称获取路径名
     *
     * @param classFullName
     */
    public static String getClassPath(String classFullName, String splitter) {
        return classFullName.substring(1, classFullName.lastIndexOf(splitter));
    }

    /**
     * 根据类全路径名称获取路径名
     *
     * @param classFullName
     */
    public static String getClassFullPath(String classFullName) {
        return classFullName.substring(1, classFullName.length());
    }

    /**
     * 根据路径获取类名称
     * @param cfile
     * @param packageName
     * @return
     */
    public static String getSplittedClassName(File cfile, String packageName) {
        if (cfile == null) {
            return null;
        }

        String fmtPath = cfile.getPath().replace("/", ".").replace("\\", ".");
        if (fmtPath.contains(packageName)) {
            String name = fmtPath.substring(fmtPath.indexOf(packageName), fmtPath.length());
            return name;
        }

        return null;
    }

    /**
     * 新行
     * @return
     */
    public static String newLine(){
        return "\n";
    }

    /**
     * 缩进4
     * @return
     */
    public static String new4Tab(){
        return "    ";
    }

    /**
     * 缩进2
     * @return
     */
    public static String new3Tab(){
        return "   ";
    }

    /**
     * 变量名匹配
     * @param variableName
     * @return
     */
    public static String getVariableNameAppendix(String variableName) {
        if (!variableName.contains(VARIABLE_SPLITTER)) {
            return null;
        }
        return VARIABLE_SPLITTER + variableName.split(VARIABLE_SPLITTER)[1];
    }

}
