package com.modou.coeus.handler;

import com.modou.coeus.common.ClassRouter;
import com.modou.coeus.node.CoeusClassNode;
import com.modou.coeus.node.CoeusMethodNode;
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.util.List;
import java.util.ListIterator;

/**
 * @program: coeus
 * @description: 初始化类节点的信息
 * @author: hu_pf
 * @create: 2021-03-08 20:38
 **/
public class InitClassNodeOperate implements ClassNodeOperate{

    private final static String INIT_METHOD_NAME = "<clinit>";


    public void operate(CoeusClassNode classNode) {

        ClassNode cn = classNode.getMetadata();

        List<MethodNode> methods = cn.methods;

        classNode.setSuperName(cn.superName);
        classNode.setInterfaceNames(cn.interfaces);

        for (MethodNode methodNode : methods){
            if (INIT_METHOD_NAME.equals(methodNode.name)){
                continue;
            }
            CoeusMethodNode coeusMethodNode = new CoeusMethodNode(methodNode.name,methodNode.desc);

            coeusMethodNode.initMethodInfo(methodNode);

            classNode.addMethod(coeusMethodNode);
        }
    }
}
