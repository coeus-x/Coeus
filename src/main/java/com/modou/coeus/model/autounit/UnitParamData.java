package com.modou.coeus.model.autounit;

import lombok.Data;

public class UnitParamData {

    //元数据的类名称，诸如 java.lang.String, java.lang.Integer
    private String className;

    //元数据的值，如果是java.lang.Integer, 则值为0
    private String newName;

    //元数据的mock值，如果是java.lang.Integer， 则值为Mockito.anyInt()
    private String mockName;

    //是否元数据类型，false：是  true：不是
    private boolean analysisFields = false;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getMockName() {
        return mockName;
    }

    public void setMockName(String mockName) {
        this.mockName = mockName;
    }

    public boolean isAnalysisFields() {
        return analysisFields;
    }

    public void setAnalysisFields(boolean analysisFields) {
        this.analysisFields = analysisFields;
    }
}
