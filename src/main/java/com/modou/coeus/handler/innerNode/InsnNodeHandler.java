package com.modou.coeus.handler.innerNode;

import com.modou.coeus.node.CoeusMethodNode;
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;

/**
 * @program: Coeus
 * @description: 内部节点的处理类
 * @author: hu_pf
 * @create: 2021-08-13 21:30
 **/
public interface InsnNodeHandler<T extends AbstractInsnNode> {

    void invoke(T t, CoeusMethodNode coeusMethodNode);

    Class getClassType();
}
