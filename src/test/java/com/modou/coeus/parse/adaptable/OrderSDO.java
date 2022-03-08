package com.modou.coeus.parse.adaptable;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 17:29
 * 商品
 **/
public interface OrderSDO extends IAdaptable{

    String invoke();

    String getName();

    void setName(String name);

}
