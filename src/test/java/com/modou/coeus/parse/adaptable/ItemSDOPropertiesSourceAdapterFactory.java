package com.modou.coeus.parse.adaptable;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 17:22
 **/
public class ItemSDOPropertiesSourceAdapterFactory implements IAdapterFactory{
    @Override
    public Object getAdapter(Object adaptableObject, Class adapterType) {

        if (adapterType == IPropertySource.class){
            return new ItemSDOPropertySource((ItemSDO)adaptableObject);
        }
        return null;
    }

    @Override
    public Class[] getAdapterList() {
        return new Class[]{IPropertySource.class};
    }
}
