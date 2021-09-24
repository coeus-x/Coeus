package com.modou.coeus.parse.unit;

import com.modou.coeus.ability.autounit.UnitBootStrap;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2021-09-23 16:41
 **/
public class UnitBootStrapTest {

    public static void main(String[] args) {
        UnitBootStrap unitBootStrap = new UnitBootStrap();
        unitBootStrap.setScanPackagePath("com.modou.coeus.parse.unit.data");
        unitBootStrap.setImportPackagePath("com.modou.coeus.parse.unit.data.test");
        unitBootStrap.start();
    }
}
