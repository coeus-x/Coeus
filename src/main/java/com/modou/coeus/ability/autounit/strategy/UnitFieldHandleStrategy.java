package com.modou.coeus.ability.autounit.strategy;


import com.modou.coeus.ability.autounit.UnitClassMethod;
import com.modou.coeus.model.autounit.UnitClassType;
import com.modou.coeus.model.autounit.UnitParamData;
import com.modou.coeus.model.autounit.UnitStrategyContent;
import com.modou.coeus.model.autounit.context.UnitInjectModel;
import com.modou.coeus.model.autounit.context.UnitMockContext;
import com.modou.coeus.model.autounit.context.UnitMockModel;
import com.modou.coeus.utils.MetaDataUtil;
import com.modou.coeus.utils.RegexUtil;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
public class UnitFieldHandleStrategy implements IUnitBuildStrategy {

    @Override
    public UnitStrategyContent build(UnitClassMethod unitClassMethod, UnitMockContext unitMockContext) {

        StringBuilder importBuilder = new StringBuilder();

        CtField[] declaredFields = unitClassMethod.getCtClass().getDeclaredFields();

        String classFullName = unitClassMethod.getCtClass().getName();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@InjectMocks").append(RegexUtil.newLine());
        stringBuilder.append(RegexUtil.new4Tab()).append("private " + RegexUtil.getClassName(classFullName) + " " + RegexUtil.getClassVariableName(classFullName)).append(";").append(RegexUtil.newLine());
        stringBuilder.append(RegexUtil.newLine());

        importBuilder.append("import " + classFullName + ";\n");

        //添加到上下文，方便后面取用
        fillInjectMocksInfo2Context(unitMockContext, classFullName);


        //生成field模板内容
        for (CtField declaredField : declaredFields) {
            //校验注入标签
            if (allow(declaredField)) {
                //生成mock内容
                stringBuilder.append(RegexUtil.newLine()).append(RegexUtil.new4Tab()).append("@Mock").append(RegexUtil.newLine());
                String mockClassName = RegexUtil.getClassName(declaredField.getFieldInfo().getDescriptor().replace(";", ""), "/");
                String mockVariaName = declaredField.getFieldInfo().getName();
                stringBuilder.append(RegexUtil.new4Tab()).append("private " + mockClassName + " " + mockVariaName).append(";");
                //生成import内容
                importBuilder.append("import " + RegexUtil.getClassFullPath(declaredField.getFieldInfo().getDescriptor()).replace("/", ".") + "\n");
                //添加到上下文
                fillMocksInfo2Context(unitClassMethod.getClazz(), unitMockContext, RegexUtil.getClassName(mockVariaName));
            }
        }

        //组装并返回
        UnitStrategyContent content = new UnitStrategyContent();
        content.getContent().put(UnitClassType.inject_field, stringBuilder);
        content.getContent().put(UnitClassType.import_path, importBuilder);
        return content;
    }

    /**
     * 添加上下文信息， @InjectMocks
     *
     * @param unitMockContext
     * @param injectClassFullName
     */
    private void fillInjectMocksInfo2Context(UnitMockContext unitMockContext, String injectClassFullName) {
        if (unitMockContext == null) {
            unitMockContext = new UnitMockContext();
        }
        //设置injectMocks
        UnitInjectModel unitInjectModel = new UnitInjectModel();
        unitInjectModel.setClassName(RegexUtil.getClassVariableName(injectClassFullName));
        unitMockContext.setUnitInjectModel(unitInjectModel);
    }

    /**
     * 添加上下文信息， @Mock
     *
     * @param unitMockContext
     * @param mockClassName
     */
    private void fillMocksInfo2Context(Class clazz, UnitMockContext unitMockContext, String mockClassName) {

        //设置mocks
        UnitMockModel unitMockModel = new UnitMockModel();
        unitMockModel.setClassName(mockClassName);

        //设置class
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals(mockClassName)) {
                unitMockModel.setClazz(field.getType());
                break;
            }
        }

        unitMockContext.getUnitMockModelList().add(unitMockModel);
    }

    /**
     * 校验spring的@Resource或者@Autowired标签，以便于使结果更准确
     *
     * @param declaredField
     * @return
     */
    private boolean allow(CtField declaredField) {
        try {
            CtClass type = declaredField.getType();
            Set<String> allSupper = getAllSuperclasses(type)
                    .stream()
                    .map(CtClass::getName)
                    .collect(Collectors.toSet());

            //默认放行
            if (allSupper == null) {
                return true;
            }

            //日志组件，无需注入
            if (allSupper.contains(Logger.class.getCanonicalName())) {
                return false;
            }

            //基础类型，无需注入
            for (String setName : allSupper) {
                UnitParamData metaParamData = MetaDataUtil.getMetaParamData(setName);
                if (metaParamData != null && !metaParamData.isAnalysisFields()) {
                    return false;
                }
            }

            //放行
            return true;
        } catch (NotFoundException e) {
            log.error("UnitFieldHandleStrategy.checkAnnotation error ", e);
            return true;
        }
    }

    /**
     * 获取父类信息
     *
     * @param ctClass
     * @return
     * @throws NotFoundException
     */
    private static Set<CtClass> getAllSuperclasses(CtClass ctClass) throws NotFoundException {
        HashSet<CtClass> ctClasses = new HashSet<>();
        while (ctClass != null) {
            ctClasses.add(ctClass);
            CtClass[] interfaces = ctClass.getInterfaces();
            Collections.addAll(ctClasses, interfaces);
            ctClass = ctClass.getSuperclass();
        }
        return ctClasses;
    }
}
