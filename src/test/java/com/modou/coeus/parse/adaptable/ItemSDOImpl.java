package com.modou.coeus.parse.adaptable;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 20:38
 **/
public class ItemSDOImpl implements ItemSDO{

    private String name = "ItemSDOImpl";


    @Override
    public String invoke() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getAdapter(Class clazz) {

        if (clazz.equals(ChangeName.class)){
            return new ItemSDOChangeNameAdapterFactory().getAdapter(this,clazz);
        }else if (clazz.equals(IPropertySource.class)){
            return new ItemSDOPropertiesSourceAdapterFactory().getAdapter(this,clazz);
        }else {
            return new ItemSDOMutilInvokeAdapterFactory().getAdapter(this,clazz);
        }
    }
}
