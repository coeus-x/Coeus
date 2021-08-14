package com.modou.coeus.handler;

import com.modou.coeus.node.CoeusClassNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

/**
 * @program: coeus
 * @description: 类节点的操作接口
 * @author: hu_pf
 * @create: 2021-03-08 20:36
 **/
public interface ClassNodeOperate {

    void operate(CoeusClassNode coeusClassNode);
}
