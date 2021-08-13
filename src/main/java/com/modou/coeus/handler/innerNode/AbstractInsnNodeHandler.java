package com.modou.coeus.handler.innerNode;

import com.modou.coeus.node.CoeusMethodNode;
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;

/**
 * @program: Coeus
 * @description: 内部方法处理抽象类
 * @author: hu_pf
 * @create: 2021-08-13 21:34
 **/
public abstract class AbstractInsnNodeHandler<T extends AbstractInsnNode> implements InsnNodeHandler<T> {

    @Override
    public void invoke(T t, CoeusMethodNode coeusMethodNode) {
        doInvoke(t,coeusMethodNode);
    }

    public abstract void doInvoke(T t, CoeusMethodNode coeusMethodNode);

}
