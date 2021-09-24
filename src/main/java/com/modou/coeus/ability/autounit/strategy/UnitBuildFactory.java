package com.modou.coeus.ability.autounit.strategy;


import com.modou.coeus.ability.autounit.UnitClassMethod;
import com.modou.coeus.model.autounit.UnitClassType;
import com.modou.coeus.model.autounit.UnitStrategyContent;
import com.modou.coeus.model.autounit.context.UnitMockContext;
import com.modou.coeus.utils.FileOpsUtil;
import com.modou.coeus.utils.RegexUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 构建
 * @date 2021-07-30 17:38
 */
@Slf4j
public class UnitBuildFactory {

    /**
     * 构建测试类内容, 将解析完毕的内容组装成一个测试类
     *
     * @return
     */
    public static String makeContent(UnitClassMethod unitClassMethod, String packageName) {

        //上下文信息
        UnitMockContext mockContext = new UnitMockContext();

        //获取类模板
        String classTemplateContent = FileOpsUtil.readTemplate("template/ClassTemplate");

        //替换掉packageName
        classTemplateContent = classTemplateContent.replace(UnitClassType.template_package_name.getExpr(), packageName);

        //替换类名
        classTemplateContent = classTemplateContent.replace(UnitClassType.test_class_name.getExpr(), RegexUtil.getFormatedClassName(unitClassMethod.getCtClass().getName()));

        //组装field并替换
        UnitStrategyContent fieldContent = makeInjectField(unitClassMethod, mockContext);
        classTemplateContent = classTemplateContent.replace(UnitClassType.inject_field.getExpr(), fieldContent.getContent().get(UnitClassType.inject_field));

        //替换模板中的方法体
        UnitStrategyContent methodContent = makeMethodBody(unitClassMethod, mockContext);
        classTemplateContent = classTemplateContent.replace(UnitClassType.method_body.getExpr(), methodContent.getContent().get(UnitClassType.method_body));

        //替换import package
        for (String importPackage : mockContext.getUnitImportPackageList()) {
            fieldContent.getContent().get(UnitClassType.import_path).append( "import "+importPackage + ";\n");
        }
        classTemplateContent = classTemplateContent.replace(UnitClassType.import_path.getExpr(), fieldContent.getContent().get(UnitClassType.import_path));

        //最终返回结果
        return classTemplateContent;

    }

    /**
     * 构建注入对象
     */
    private static UnitStrategyContent makeInjectField(UnitClassMethod unitClassMethod, UnitMockContext unitMockContext) {
        IUnitBuildStrategy unitBuildStrategy = new UnitFieldHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        return context.build(unitClassMethod, unitMockContext);
    }

    /**
     * 构建方法体内容
     */
    private static UnitStrategyContent makeMethodBody(UnitClassMethod unitClassMethod, UnitMockContext unitMockContext) {
        IUnitBuildStrategy unitBuildStrategy = new UnitMethodHandleStrategy();
        UnitBuildContext context = new UnitBuildContext(unitBuildStrategy);
        return context.build(unitClassMethod, unitMockContext);
    }

}
