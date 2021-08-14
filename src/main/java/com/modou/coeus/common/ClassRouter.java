package com.modou.coeus.common;

import com.modou.coeus.handler.innerNode.DefaultMethodInsnNodeHandler;
import com.modou.coeus.handler.innerNode.InsnNodeHandler;
import com.modou.coeus.handler.innerNode.MethodInsnNodeHandler;
import com.modou.coeus.node.CoeusClassNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Coeus
 * @description: 路由信息
 * @author: hu_pf
 * @create: 2021-08-13 20:36
 **/
public class ClassRouter {

    private final Map<String, CoeusClassNode> classRouteMap = new HashMap<>();

    // 处理类的路由
    private static Map<Class, InsnNodeHandler> insnNodeHandlerMap = new HashMap<>();

    private static ClassRouter classRouter= new ClassRouter();


    public void putClass(String className, ClassNode cn){
        CoeusClassNode coeusClassNode = new CoeusClassNode(className);
        coeusClassNode.initMetadata(cn);
        classRouteMap.put(className,coeusClassNode);
    }

    public CoeusClassNode getClass(String className){
        return classRouteMap.get(className);
    }

    {
        // todo 内部类型处理器要改成动态加载实现类而不是手动加
        MethodInsnNodeHandler methodInsnNodeHandler = new MethodInsnNodeHandler();
        insnNodeHandlerMap.put(methodInsnNodeHandler.getClassType(),methodInsnNodeHandler);
    }

    private ClassRouter(){

    }

    public static ClassRouter getInstance(){
        return classRouter;
    }

    /**
    * @Description: 获得内部处理节点器
    * @Param: [classType]
    * @return: com.modou.coeus.handler.innerNode.InsnNodeHandler
    * @Author: hu_pf
    * @Date: 2021/8/13
    */
    public InsnNodeHandler getInsnNodeHandler(Class classType){
        InsnNodeHandler insnNodeHandler = insnNodeHandlerMap.get(classType);
        if (insnNodeHandler == null){
            insnNodeHandler = new DefaultMethodInsnNodeHandler();
        }
        return insnNodeHandler;
    }

}
