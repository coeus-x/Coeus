package com.modou.coeus.ability.autounit;

import com.modou.coeus.model.autounit.UnitClassContent;
import com.modou.coeus.utils.RegexUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @program: coeus
 * @description:
 * @author: hu_pf
 * @create: 2021-09-23 17:18
 **/
public class UnitClassGenerator {


    /**
     * 生成测试类
     *
     * @param unitClassBuilds
     */
    public void generate(List<UnitClassContent> unitClassBuilds) {
        //参数校验
        if (unitClassBuilds == null || unitClassBuilds.size() <= 0) {
            return;
        }
        //遍历循环
        for (UnitClassContent unitClassBuild : unitClassBuilds) {
            createTestClass(unitClassBuild);
        }
    }

    /**
     * 创建类文件
     *
     * @param unitClassBuild
     */
    public boolean createTestClass(UnitClassContent unitClassBuild) {
        try {

            String pathName = unitClassBuild.getClassName().replace(".", "/");

            //创建目录
            String directory = pathName.substring(0, pathName.lastIndexOf("/"));
            File directories = new File(directory);
            directories.mkdirs();

            //创建文件
            String fmtFileName = RegexUtil.getFormatedClassName(unitClassBuild.getClassName()) + ".java";
            PrintWriter writer = new PrintWriter(directory + "/" + fmtFileName , "UTF-8");
            writer.write(unitClassBuild.getContent());
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
