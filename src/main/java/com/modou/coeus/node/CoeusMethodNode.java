package com.modou.coeus.node;

import com.modou.coeus.common.ClassRouter;
import com.modou.coeus.common.Constant;
import com.modou.coeus.handler.innerNode.InsnNodeHandler;
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @program: coeus
 * @description: 方法节点信息
 * @author: hu_pf
 * @create: 2021-03-08 10:02
 **/
public class CoeusMethodNode {

    // 全路径名
    private String allPath;

    private String id;

    // 别名
    private String name;

    // 描述：入参和出参，可以在方法中唯一确认到一个方法
    private String desc;

    // 入参集合 全路径名描述
    private List<String> requestParams;

    // 出参 全路径名描述
    private String returnParam;

    // 访问权限 具体描述值请看 AccessConstant
    private Integer access;

    // 抛出的异常信息 记录全路径名
    private List<String> throwExceptions;
    
    private List<String> invokeInfos;

    public CoeusMethodNode(){

    }

    public CoeusMethodNode(String name,String desc){
        this.name = name;
        this.desc = desc;
        this.id = generateId(name,desc);
    }
    
    /**
    * @Description: 添加执行的方法信息
    * @Param: [className, methodName, desc]
    * @return: void
    * @Author: hu_pf
    * @Date: 2021/8/13
    */
    public void addInvokeMethodInfo(String className,String methodName,String desc){
        if (invokeInfos == null){
            invokeInfos = new ArrayList<>();
        }
        invokeInfos.add(className + Constant.SPLIT + methodName + Constant.SPLIT + desc);
    }


    public void visit(InsnNodeHandler insnNodeHandler,AbstractInsnNode abstractInsnNode){
        insnNodeHandler.invoke(abstractInsnNode,this);
    }

    public String getId(){

        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setAccess(Integer access){
        this.access = access;
    }

    public static String generateId(String name,String desc){
        return name + Constant.SPLIT + desc;
    }

    /**
    * @Description: 初始化方法的信息
    * @Param: [methodNode]
    * @return: void
    * @Author: hu_pf
    * @Date: 2021/8/18
    */
    public void initMethodInfo(MethodNode methodNode){
        ClassRouter classRouter = ClassRouter.getInstance();

        ListIterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();

        setAccess(methodNode.access);

        while (iterator.hasNext()) {
            AbstractInsnNode next = iterator.next();

            visit(classRouter.getInsnNodeHandler(next.getClass()),next);
        }
    }
}
