package com.modou.coeus.node;

import com.modou.coeus.handler.ClassNodeOperate;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: coeus
 * @description: 类节点信息
 * @author: hu_pf
 * @create: 2021-03-08 10:02
 **/
public class CoeusClassNode {

    // 包信息
    private String packageInfo;

    private String name;

    // 全路径名
    private String allPath;

    // 别名
    private String alias;

    // 父类信息
    private List<CoeusClassNode> parentClazz;

    // 子类信息
    private List<CoeusClassNode> childClazz;

    // 类描述信息 具体值请看 ClassTypeConstant
    private int classType;

    // 类中包含的方法
    private List<CoeusMethodNode> methods;

    // 类中包含的注解
    private List<CoeusAnnotationNode> annotations;

    // 类中包含的成员变量
    private List<CoeusParamNode> params;


    public CoeusClassNode(String name){
        this.name = name;
    }

    public CoeusClassNode(){
    }

    /**
    * @Description: 添加方法
    * @Param: [coeusMethodNode]
    * @return: void
    * @Author: hu_pf
    * @Date: 2021/8/13
    */
    public void addMethod(CoeusMethodNode coeusMethodNode){
        if (methods == null){
            methods = new ArrayList<>();
        }
        methods.add(coeusMethodNode);
    }


    /**
    * @Description: 操作数据
    * @Param: [classNodeOperate]
    * @return: void
    * @Author: hu_pf
    * @Date: 2021/3/8
    */
    public void visit(ClassNodeOperate classNodeOperate){
        classNodeOperate.operate(this);
    }
}
