package com.modou.coeus.node;

import java.util.List;

/**
 * @program: coeus
 * @description: 方法节点信息
 * @author: hu_pf
 * @create: 2021-03-08 10:02
 **/
public class CoeusMethodNode {

    // 全路径名
    private String allPath;

    // 别名
    private String alias;

    // 入参集合 全路径名描述
    private List<String> requestParams;

    // 出参 全路径名描述
    private String returnParam;

    // 访问权限 具体描述值请看 AccessConstant
    private Integer access;

    // 抛出的异常信息 记录全路径名
    private List<String> throwExceptions;

}
