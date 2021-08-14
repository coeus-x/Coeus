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

    private static String INIT_METHOD_NAME = "<clinit>";

    private static final ClassRouter classRouter = ClassRouter.getInstance();

    public void operate(CoeusClassNode classNode) {

        ClassNode cn = classNode.getMetadata();

        List<MethodNode> methods = cn.methods;

        classNode.setSuperName(cn.superName);

        for (MethodNode methodNode : methods){
            if (INIT_METHOD_NAME.equals(methodNode.name)){
                continue;
            }
            CoeusMethodNode coeusMethodNode = new CoeusMethodNode(methodNode.name,methodNode.desc);

            ListIterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();

            while (iterator.hasNext()) {
                AbstractInsnNode next = iterator.next();
                coeusMethodNode.visit(classRouter.getInsnNodeHandler(next.getClass()),next);
            }
            classNode.addMethod(coeusMethodNode);
        }
    }
}
