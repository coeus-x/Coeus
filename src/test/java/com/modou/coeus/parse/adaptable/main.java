package com.modou.coeus.parse.adaptable;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2022-03-07 21:49
 **/
public class main {

    public static void main(String[] args) {
        ItemSDOImpl itemSDO = new ItemSDOImpl();

        ItemSDOChangeName adapter = (ItemSDOChangeName)itemSDO.getAdapter(ChangeName.class);

        adapter.changeName("test1111_");

        System.out.println(itemSDO.invoke());

        ItemSDOMutilInvoke mutilInvoke = (ItemSDOMutilInvoke)itemSDO.getAdapter(MutilInvoke.class);
        mutilInvoke.invoke(4);
    }
}
