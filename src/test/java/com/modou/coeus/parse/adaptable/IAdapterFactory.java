package com.modou.coeus.parse.adaptable;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 17:24
 **/
public interface IAdapterFactory {

    Object getAdapter(Object adaptableObject,Class adapterType);

    Class[] getAdapterList();
}
