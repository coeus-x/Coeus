package com.modou.coeus.ability.autounit;

import javassist.CtMethod;
import lombok.Data;

import java.lang.reflect.Method;


public class UnitMethodPair {

    private CtMethod ctMethod;

    private Method method;

    public CtMethod getCtMethod() {
        return ctMethod;
    }

    public void setCtMethod(CtMethod ctMethod) {
        this.ctMethod = ctMethod;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
