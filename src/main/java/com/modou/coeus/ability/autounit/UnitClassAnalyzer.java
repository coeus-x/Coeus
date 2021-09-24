package com.modou.coeus.ability.autounit;

import com.modou.coeus.node.CoeusClassNode;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Slf4j
public class UnitClassAnalyzer {

    /**
     * 根据java文件，获取需要生成测试用例的类
     *
     * @param classes
     */
    public List<UnitClassMethod> analysis(Set<CoeusClassNode> classes) {

        //得到当前对象池
        ClassPool pool = new ClassPool(true);

        //插入当前类加载器
        pool.insertClassPath(new ClassClassPath(UnitBootStrap.class));

        List<UnitClassMethod> unitClassMethodList = new ArrayList<>();

        for (CoeusClassNode coeusClassNode : classes) {
            Class aClass = coeusClassNode.getaClass();
            try {
                CtClass ctClass = pool.get(aClass.getName());
                //保存类对象
                UnitClassMethod unitClassMethod = new UnitClassMethod();
                unitClassMethod.setCtClass(ctClass);
                unitClassMethod.setClazz(aClass);

                //保存方法列表
                CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
                for (CtMethod declaredMethod : declaredMethods) {
                    //不加@UnitExclude 且 public方法才生成单测类
                    if (declaredMethod.getModifiers() == 1) {
                        UnitMethodPair unitMethodPair = new UnitMethodPair();
                        unitMethodPair.setCtMethod(declaredMethod);
                        for (Method method : aClass.getDeclaredMethods()) {
                            //如果是同一个方法，则暂存
                            if (method.getName().equals(declaredMethod.getName())) {
                                unitMethodPair.setMethod(method);
                            }
                        }
                        unitClassMethod.getMethodPairs().add(unitMethodPair);
                    }
                }
                unitClassMethodList.add(unitClassMethod);
            } catch (Exception e) {
                log.error("UnitClassAnalyzer.analysis get class error", e);
            }

        }
        return unitClassMethodList;
    }

    public List<UnitClassMethod> batch(Set<Class> classes) {

        //得到当前对象池
        ClassPool pool = new ClassPool(true);

        //插入当前类加载器
        pool.insertClassPath(new ClassClassPath(UnitBootStrap.class));

        List<UnitClassMethod> unitClassMethodList = new ArrayList<>();

        for (Class aClass : classes) {
            try {
                CtClass ctClass = pool.get(aClass.getName());
                //保存类对象
                UnitClassMethod unitClassMethod = new UnitClassMethod();
                unitClassMethod.setCtClass(ctClass);
                unitClassMethod.setClazz(aClass);
                //保存方法列表
                CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
                for (CtMethod declaredMethod : declaredMethods) {
                    if (declaredMethod.getModifiers() == 1) {
                        UnitMethodPair unitMethodPair = new UnitMethodPair();
                        unitMethodPair.setCtMethod(declaredMethod);
                        for (Method method : aClass.getDeclaredMethods()) {
                            //如果是同一个方法，则暂存
                            if (method.getName().equals(declaredMethod.getName())) {
                                unitMethodPair.setMethod(method);
                            }
                        }
                        unitClassMethod.getMethodPairs().add(unitMethodPair);
                    }
                }
                unitClassMethodList.add(unitClassMethod);
            } catch (Exception e) {
                log.error("UnitClassAnalyzer.analysis get class error", e);
            }
        }
        return unitClassMethodList;
    }
}
