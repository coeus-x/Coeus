package com.modou.coeus;

import com.alibaba.fastjson.JSONObject;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;

/**
 * @program: com.zhhiyp.incubator.asm
 * @description: 解析class类
 * @author: hu_pf
 * @create: 2021-01-12 11:42
 **/
public class ParseClass {



    public static void parseSourceClass(InputStream in) {
        ClassNode cn = new ClassNode();
        try {
            ClassReader cr = new ClassReader(in);
            cr.accept(cn, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String className = cn.name;


    }
}
