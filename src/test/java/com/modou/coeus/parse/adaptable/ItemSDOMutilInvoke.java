package com.modou.coeus.parse.adaptable;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 22:03
 **/
public class ItemSDOMutilInvoke implements MutilInvoke{

    private ItemSDO itemSDO;

    public ItemSDOMutilInvoke(ItemSDO itemSDO){
        this.itemSDO = itemSDO;
    }

    @Override
    public void invoke(Integer integer) {
        for (int i = 0; i < integer; i++) {
            System.out.println(itemSDO.invoke());
        }
    }
}
