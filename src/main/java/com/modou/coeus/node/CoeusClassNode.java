package com.modou.coeus.node;

import com.modou.coeus.common.ClassRouter;
import com.modou.coeus.constant.ClassTypeConstant;
import com.modou.coeus.handler.ClassNodeOperate;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

import java.util.*;

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

    // 继承类信息
    private String superName;

    private Boolean hasSuperClass = false;
    // 接口类信息
    private List<String> interfaceNames = new ArrayList<>();

    // 子类信息
    private List<CoeusClassNode> childClazz;

    // 类描述信息 具体值请看 ClassTypeConstant
    private int classType;

    // 类中包含的方法
    private List<CoeusMethodNode> methods;

    // id 和方法的路由关系
    private Map<String,CoeusMethodNode> routIdAndMethodMap = new HashMap<>();

    // 名称和方法的路由
    private Map<String,CoeusMethodNode> routNameAndMethodMap = new HashMap<>();

    private Map<String,Integer> routNameAndMethodMapCount = new HashMap<>();

    // 类中包含的注解
    private List<CoeusAnnotationNode> annotations;

    private Set<String> invokeClassNodes;

    // 类中包含的成员变量
    private List<CoeusParamNode> params;

    private ClassNode metaData;

    private Class aClass;


    public CoeusClassNode(String name){
        try {
            this.aClass = Class.forName(name.replaceAll("/", "."));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        routIdAndMethodMap.put(coeusMethodNode.getId(), coeusMethodNode);

        routNameAndMethodMap.put(coeusMethodNode.getName(), coeusMethodNode);

        Integer count = routNameAndMethodMapCount.getOrDefault(coeusMethodNode.getName(), 0);
        routNameAndMethodMapCount.put(coeusMethodNode.getName(),count+1);

        addInvokeClassNodes(coeusMethodNode);
        methods.add(coeusMethodNode);
    }

    private void addInvokeClassNodes(CoeusMethodNode coeusMethodNode){
        if (invokeClassNodes == null){
            invokeClassNodes = new HashSet<>();
        }
        invokeClassNodes.addAll(coeusMethodNode.getInvokeInfosClass());
    }

    public void initMetadata(ClassNode classNode){
        this.metaData = classNode;
    }

    public ClassNode getMetadata(){
        return metaData;
    }


    public CoeusMethodNode getMethod(String name){
        CoeusMethodNode coeusMethodNode = routNameAndMethodMap.get(name);
        // 如果本类找不到就向上父类去寻找
        if (coeusMethodNode == null && hasSuperClass){
            coeusMethodNode = ClassRouter.getInstance().getClass(this.superName).getMethod(name);
        }
        return coeusMethodNode;
    }

    public CoeusMethodNode getMethod(String name,String desc){
        CoeusMethodNode coeusMethodNode = routIdAndMethodMap.get(CoeusMethodNode.generateId(name, desc));
        // 如果本类找不到就向上父类去寻找
        if (coeusMethodNode == null && hasSuperClass){
            coeusMethodNode = ClassRouter.getInstance().getClass(this.superName).getMethod(name,desc);
        }
        return coeusMethodNode;
    }

    public void setSuperName(String superName){
        this.superName = superName;
        this.hasSuperClass = true;
    }

    public void setInterfaceNames(List<String> interfaceNames){
        this.interfaceNames = interfaceNames;
    }

    public void setClassType(int classType){
        this.classType = classType;
    }

    public Set<String> getInvokeClassNodes(){
        return invokeClassNodes;
    }

    public Boolean isInterface(){
        return this.aClass.isInterface();
    }

    public Boolean isEnum(){
        return this.aClass.isEnum();
    }

    public Class getaClass(){
        return this.aClass;
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
