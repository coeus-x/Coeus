package com.modou.coeus.ability.autounit;

import com.modou.coeus.model.autounit.UnitClassContent;
import com.modou.coeus.node.CoeusClassNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

/**
 * @program: coeus
 * @description: 单元测试
 * @author: hu_pf
 * @create: 2021-09-23 16:34
 **/
@Slf4j
@Data
public class UnitBootStrap {

    /**
     * 批量生成开关，默认关闭
     * packageScanSwitch = true: 将指定packageName中的类自动生成单测类
     * packageScanSwitch = false: 不会将指定packageName中的类生成单测类， 默认此开关开启
     */
    private Boolean batchGenerate = false;

    /**
     * 扫描的包路径
     */
    private String scanPackagePath;

    /**
     * 生成单测类时候，import包路径
     */
    private String importPackagePath;


    public void start(){

        //扫描包路径校验
        if (scanPackagePath == null || scanPackagePath.equals("")) {
            log.error("包扫描路径不能为空，请重试");
            return;
        }

        //导入包路径校验
        if (importPackagePath == null || importPackagePath.equals("")) {
            log.error("包导入路径未配置，将默认使用包扫描路径配置");
            importPackagePath = scanPackagePath;
        }

        UnitClassScanner unitClassScanner = new UnitClassScanner();
        UnitClassAnalyzer analyzer = new UnitClassAnalyzer();
        List<UnitClassMethod> analysis = analyzer.analysis(unitClassScanner.scan(scanPackagePath));

        UnitContentBuilder unitContentBuilder = new UnitContentBuilder();
        List<UnitClassContent> unitClassBuilds = unitContentBuilder.build(analysis, importPackagePath);

        //4. 生成单测类并保存
        UnitClassGenerator generator = new UnitClassGenerator();
        generator.generate(unitClassBuilds);
    }

}
