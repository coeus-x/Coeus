package com.modou.coeus.model.autounit.context;

import lombok.Data;


public class UnitInjectModel {

    private String className;

    private String methodName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
