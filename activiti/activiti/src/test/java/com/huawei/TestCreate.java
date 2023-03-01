package com.huawei;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * @author king
 * @date 2023/2/8-23:22
 * @Desc
 */
public class TestCreate {

    @Test
    public void testCreate() {
        // getDefaultProcessEngine()方法从源码中可以看出，默认会读取activiti.cfg.xml文件
        // 创建ProcessEngine对象时，会创建与流程相关的25张表
        /**
         * act_ge_   general 通用表
         * act_re_   respository 包含了流程定义和流程静态资源 （图片，规则，等等)
         * act_ru_   runtime 运行时的表
         * act_hi_   历史数据，比如历史流程实例， 变量，任务等等。
         */
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println("processEngine = " + processEngine);
    }
    @Test
    public void testCreate2() {
        // getDefaultProcessEngine()方法从源码中可以看出，默认会读取activiti.cfg.xml文件
        // 创建ProcessEngine对象时，会创建与流程相关的25张表
        /**
         * act_ge_   general 通用表
         * act_re_   respository 包含了流程定义和流程静态资源 （图片，规则，等等)
         * act_ru_   runtime 运行时的表
         * act_hi_   历史数据，比如历史流程实例， 变量，任务等等。
         */
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        System.out.println("processEngine = " + processEngine);
        // 不使用默认方式创建
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml", "");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
    }


}
