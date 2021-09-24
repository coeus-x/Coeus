package com.modou.coeus.ability.autounit;

import javassist.CtClass;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


public class UnitClassMethod {

    /**
     * 测试类CtClass类型对象
     */
    private CtClass ctClass;

    /**
     * 测试类Class类型对象
     */
    private Class clazz;

    /**
     * 测试方法
     */
    private List<UnitMethodPair> methodPairs = new ArrayList<>();

    public CtClass getCtClass() {
        return ctClass;
    }

    public void setCtClass(CtClass ctClass) {
        this.ctClass = ctClass;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public List<UnitMethodPair> getMethodPairs() {
        return methodPairs;
    }

    public void setMethodPairs(List<UnitMethodPair> methodPairs) {
        this.methodPairs = methodPairs;
    }
}
