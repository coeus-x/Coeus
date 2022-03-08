package com.modou.coeus.parse.adaptable;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 17:24
 **/
public class ItemSDOPropertySource implements IPropertySource{

    private Object object;

    public ItemSDOPropertySource(Object o) {
        this.object = o;
    }

    @Override
    public void setPropertyValue(Object id, Object value) {
        String invoke = ((ItemSDOImpl) this.object).invoke();

    }
}
