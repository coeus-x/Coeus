package com.modou.coeus.parse.adaptable;

import lombok.Data;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 17:10
 **/
@Data
public class User implements IAdaptable{

    private Integer id;

    private String name;

    @Override
    public Object getAdapter(Class clazz) {
        return getModelAdapter();
    }

    private Object getModelAdapter(){
        return null;
    }
}
