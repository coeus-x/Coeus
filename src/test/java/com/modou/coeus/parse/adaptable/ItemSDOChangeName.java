package com.modou.coeus.parse.adaptable;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 21:55
 **/
public class ItemSDOChangeName implements ChangeName{

    private ItemSDO itemSDO;

    public ItemSDOChangeName(ItemSDO itemSDO){
        this.itemSDO = itemSDO;
    }

    @Override
    public void changeName(String name) {
        String name1 = itemSDO.getName();
        itemSDO.setName(name+name1);
    }
}
