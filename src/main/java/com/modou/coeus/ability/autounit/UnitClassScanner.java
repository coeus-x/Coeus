package com.modou.coeus.ability.autounit;

import com.modou.coeus.NodeFacade;
import com.modou.coeus.common.ClassRouter;
import com.modou.coeus.node.CoeusClassNode;

import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: coeus
 * @description: 扫描类文件
 * @author: hu_pf
 * @create: 2021-09-23 16:35
 **/
public class UnitClassScanner {


    public Set<CoeusClassNode> scan(String packageName){
        Set<CoeusClassNode> allClassesUsingClassLoader = findAllClassesUsingClassLoader(packageName);

        Set<CoeusClassNode> result = new HashSet<>();

        for (CoeusClassNode classNode : allClassesUsingClassLoader){
            if (classNode.isEnum() || classNode.isInterface()){
                continue;
            }
            result.add(classNode);
        }

        return result;
    }


    /**
     * 利用类加载器加载指定包名下的所有类
     * @param packageName
     * @return
     */
    public Set<CoeusClassNode> findAllClassesUsingClassLoader(String packageName) {
        URL url = ClassLoader.getSystemClassLoader().getResource(packageName.replaceAll("[.]", "/"));
        NodeFacade.buildSource(url.getPath());
        ClassRouter classRouter = ClassRouter.getInstance();
        Map<String, CoeusClassNode> nodes = classRouter.obtainClassRouteMap();
        return nodes.values().stream().parallel()
                .collect(Collectors.toSet());
    }
}
