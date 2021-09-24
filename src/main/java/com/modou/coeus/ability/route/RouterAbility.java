package com.modou.coeus.ability.route;


import com.modou.coeus.NodeFacade;
import com.modou.coeus.common.ClassRouter;
import com.modou.coeus.graph.Graph;
import com.modou.coeus.graph.ListGraph;
import com.modou.coeus.node.CoeusClassNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: Coeus
 * @description: 路由能力
 * @author: hu_pf
 * @create: 2021-08-14 14:44
 **/
public class RouterAbility {


    private ClassRouter instance;
    private Graph<String, Integer> graph;

    private static final Set<String> igroneClassName = new HashSet<String>(Arrays.asList("java/lang/Object"));

    private RouterAbility(){

    }

    public RouterAbility(String path){
        NodeFacade.buildSource(path);
        instance = ClassRouter.getInstance();
        graph = new ListGraph();
    }

    public List<String> routeByClassName(String classNameBegin,String classNameEnd){

        instance.obtainClassRouteMap().forEach((String from,  CoeusClassNode node)->{
            node.getInvokeClassNodes().forEach((String to) -> {
                if(!igroneClassName.contains(to)){
                    graph.addEdge(from, to);
                }
            });
        });
        final List<String> paths = graph.findAllPath(classNameBegin, classNameEnd);
        return paths;
    }
}
