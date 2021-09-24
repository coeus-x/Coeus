package com.modou.coeus.model.autounit.context;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 上下文共享信息, 一个测试类，会有一个InjectMocks，多个Mock，多个Spy
 * @date 2021-08-04 16:33
 */
public class UnitMockContext {

    //injectmocks信息
    private UnitInjectModel unitInjectModel;

    //import包名信息
    private Set<String> unitImportPackageList = new HashSet<>();

    //mock列表信息
    private List<UnitMockModel> unitMockModelList = new ArrayList<>();

    //spy列表信息
    private List<UnitSpyModel> unitSpyModelList = new ArrayList<>();

    //入参变量名列表
    private List<String> inputParamVariables = new ArrayList<>();

    //返回值对象类型
    private String returnType;

    public UnitInjectModel getUnitInjectModel() {
        return unitInjectModel;
    }

    public void setUnitInjectModel(UnitInjectModel unitInjectModel) {
        this.unitInjectModel = unitInjectModel;
    }

    public Set<String> getUnitImportPackageList() {
        return unitImportPackageList;
    }

    public void setUnitImportPackageList(Set<String> unitImportPackageList) {
        this.unitImportPackageList = unitImportPackageList;
    }

    public List<UnitMockModel> getUnitMockModelList() {
        return unitMockModelList;
    }

    public void setUnitMockModelList(List<UnitMockModel> unitMockModelList) {
        this.unitMockModelList = unitMockModelList;
    }

    public List<UnitSpyModel> getUnitSpyModelList() {
        return unitSpyModelList;
    }

    public void setUnitSpyModelList(List<UnitSpyModel> unitSpyModelList) {
        this.unitSpyModelList = unitSpyModelList;
    }

    public List<String> getInputParamVariables() {
        return inputParamVariables;
    }

    public void setInputParamVariables(List<String> inputParamVariables) {
        this.inputParamVariables = inputParamVariables;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
