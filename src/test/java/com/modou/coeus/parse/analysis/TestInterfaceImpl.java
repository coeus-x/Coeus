package com.modou.coeus.parse.analysis;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2021-08-14 00:52
 **/
public class TestInterfaceImpl extends TestExtend implements TestInterface {


    @Override
    public void invoke() {
        test();
    }

    private void test(){
        InvokeService invokeService = new InvokeService();
        invokeService.invoke();
    }

    void testNoDesc(){

    }

    protected void testNoProtected(){

    }


}
