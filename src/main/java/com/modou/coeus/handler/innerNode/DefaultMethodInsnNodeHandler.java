package com.modou.coeus.handler.innerNode;

import com.modou.coeus.handler.innerNode.AbstractInsnNodeHandler;
import com.modou.coeus.node.CoeusMethodNode;
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;

/**
 * @program: Coeus
 * @description: 默认的内部执行
 * @author: hu_pf
 * @create: 2021-08-13 21:54
 **/
public class DefaultMethodInsnNodeHandler extends AbstractInsnNodeHandler {


    @Override
    public void doInvoke(AbstractInsnNode abstractInsnNode, CoeusMethodNode coeusMethodNode) {

    }

    @Override
    public Class getClassType() {
        return AbstractInsnNodeHandler.class;
    }
}
