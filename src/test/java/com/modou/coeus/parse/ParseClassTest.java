package com.modou.coeus.parse;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2021-03-11 11:42
 **/
public class ParseClassTest {

    private String name = "xxx";


    public void test(){

    }

    public void test(String name){

    }

    public String returnString(){

        return "xxx";
    }

    public Student returnStudent(){

        Student student = new Student();

        student.setAddress("xx");

        return student;
    }

    public static void main(String[] args) {
        System.out.println("1");
    }

}
