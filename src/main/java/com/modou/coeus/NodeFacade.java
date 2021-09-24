package com.modou.coeus;

import com.modou.coeus.ability.route.RouterAbility;
import com.modou.coeus.common.ClassRouter;
import com.modou.coeus.node.CoeusClassNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @program: coeus
 * @description: 节点初始化
 * @author: hu_pf
 * @create: 2021-03-08 20:48
 **/
public class NodeFacade {

    public static final String IGNORE_GIT = ".git";
    public static final String IGNORE_IDEA = ".idea";
    public static final String IGNORE_CLASS = ".class";

    public static void main(String[] args) {
        String projectRoot = "D:\\my_git\\Coeus\\target\\test-classes\\com\\modou\\coeus\\parse\\chart";

        buildSource(projectRoot);

        ClassRouter instance = ClassRouter.getInstance();

        CoeusClassNode aClass = instance.getClass("com.modou.coeus.parse.analysis.TestInterfaceImpl");

        RouterAbility routerAbility = new RouterAbility(projectRoot);
        final List<String> paths = routerAbility.routeByClassName("com/modou/coeus/parse/chart/A", "com/modou/coeus/parse/chart/D");
        System.out.println(paths);
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
            if (!path.endsWith(IGNORE_GIT) || !path.endsWith(IGNORE_IDEA)) {
                //递归
                for (String child : root.list()) {
                    buildSource(path + File.separator + child);
                }
            }
        } else {
            if (root.getAbsolutePath().endsWith(IGNORE_CLASS)) {
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
