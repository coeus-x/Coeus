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
        InvokeService test = test();
        test.invoke();
    }

    private InvokeService test(){
        InvokeService invokeService = new InvokeService();
        return invokeService;
    }

    void testNoDesc(){

    }

    protected void testNoProtected(){

    }


}
