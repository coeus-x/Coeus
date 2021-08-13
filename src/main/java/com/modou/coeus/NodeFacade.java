package com.modou.coeus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @program: coeus
 * @description: 节点初始化
 * @author: hu_pf
 * @create: 2021-03-08 20:48
 **/
public class NodeFacade {

    public static void main(String[] args) {
        String projectRoot = "D:\\my_git\\MethodParser\\target\\classes\\com\\zhhiyp\\incubator\\asm\\invoke";

        buildSource(projectRoot);
    }


    //传入一个工程路径
    //搜索下面的class
    //读成流,发给解析类
    public static void buildSource(String projectRoot) {
        File root = new File(projectRoot);
        if (!root.exists()) {
            return;
        }

        if (root.isDirectory()) {
            String path = root.getAbsolutePath();
            if (!path.endsWith(".git") || !path.endsWith(".idea")) {
                //递归
                for (String child : root.list()) {
                    buildSource(path + File.separator + child);
                }
            }
        } else {
            if (root.getAbsolutePath().endsWith(".class")) {
                try {
                    FileInputStream in = new FileInputStream(root);
                    ParseClass.parseSourceClass(in);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
