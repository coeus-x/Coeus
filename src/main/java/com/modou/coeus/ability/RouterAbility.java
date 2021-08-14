package com.modou.coeus.ability;


import com.modou.coeus.NodeFacade;
import com.modou.coeus.common.ClassRouter;

/**
 * @program: Coeus
 * @description: 路由能力
 * @author: hu_pf
 * @create: 2021-08-14 14:44
 **/
public class RouterAbility {


    private ClassRouter instance;

    public RouterAbility(){

    }

    public RouterAbility(String path){
        NodeFacade.buildSource(path);
        instance = ClassRouter.getInstance();
    }

    public void routeByClassName(String classNameBegin,String classNameEnd){
        
    }
}
