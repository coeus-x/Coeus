package com.modou.coeus.parse.adaptable;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 22:05
 **/
public class ItemSDOMutilInvokeAdapterFactory implements IAdapterFactory{
    @Override
    public Object getAdapter(Object adaptableObject, Class adapterType) {
        if (adapterType == MutilInvoke.class){
            return new ItemSDOMutilInvoke((ItemSDO)adaptableObject);
        }
        return null;
    }

    @Override
    public Class[] getAdapterList() {
        return new Class[0];
    }
}
