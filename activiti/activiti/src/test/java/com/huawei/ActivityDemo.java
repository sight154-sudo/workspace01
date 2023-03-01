package com.huawei;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author king
 * @date 2023/2/13-23:44
 * @Desc
 */
public class ActivityDemo {

    /**
     * act_re_deployment 流程定义部署表，每部署一次增加一条记录
     * act_re_procdef 流程定义表，部署每个新的流程定义都会在这张表中增加一条记录
     * act_ge_bytearray 流程资源表
     */
    @Test
    public void createProcess() {
        // 创建默认流程
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取respositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().name("请假流程").addClasspathResource("bpmn/exection.bpmn20.xml").deploy();

        System.out.println("流程id: "+deploy.getId());
        System.out.println("流程名称: "+deploy.getName());
    }

    /**
     * act_hi_actinst 流程实例执行历史
     * act_hi_identitylink 流程的参与用户历史信息
     * act_hi_procinst 流程实例历史信息
     * act_hi_taskinst 流程任务历史信息
     * act_ru_execution 流程执行信息
     * act_ru_identitylink 流程的参与用户信息
     * act_ru_task 任务信息
     */
    @Test
    public void startProcess() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myEvection");

        System.out.println("流程定义Id: "+processInstance.getProcessDefinitionId());
        System.out.println("流程实例id: "+processInstance.getId());
        System.out.println("流程id: "+processInstance.getActivityId());
    }

    @Test
    public void testFindPersonTask() {
        // 查询任务
        // 获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取taskservice
        TaskService taskService = processEngine.getTaskService();
        // 创建查询,根据key与流程执行人查询任务信息
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("myEvection")
                .taskAssignee("张三")
                .list();
        // 输出信息
        for (Task task : tasks) {
            System.out.println("流程id: "+ task.getProcessInstanceId());
            System.out.println("任务id："+ task.getId());
            System.out.println("任务责任人: "+ task.getAssignee());
            System.out.println("任务名称: "+ task.getName());
        }
    }

    @Test
    public void completeTask() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取任务service
        TaskService taskService = processEngine.getTaskService();
        // 完成任务
//        taskService.complete("5005");
        // 先查询任务,再完成任务
//        Task task = taskService.createTaskQuery()
//                .processDefinitionKey("myEvection")
//                .taskAssignee("jerry")
//                .singleResult();
//        taskService.complete(task.getId());
//        Task task = taskService.createTaskQuery()
//                .processDefinitionKey("myEvection")
//                .taskAssignee("jack")
//                .singleResult();
//        taskService.complete(task.getId());
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myEvection")
                .taskAssignee("rose")
                .singleResult();
        taskService.complete(task.getId());


    }


    @Test
    public void processDefQuery() {
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取service
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myEvection")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition processDefinition : processDefinitions) {
            System.out.println("流程定义id: "+processDefinition.getId());
            System.out.println("流程部署id: "+processDefinition.getDeploymentId());
            System.out.println("流程定义名称: "+processDefinition.getName());
            System.out.println("流程定义版本: "+processDefinition.getVersion());
        }
    }

    @Test
    public void deleteDepository() {
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取service
        RepositoryService repositoryService = processEngine.getRepositoryService();
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myEvection").singleResult();
//        String deploymentId = processDefinition.getDeploymentId();
//        System.out.println("deploymentId = " + deploymentId);
        repositoryService.deleteDeployment("17501", true);
    }

    @Test
    public void downloadResource() throws IOException {
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取service
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myEvection").singleResult();
        String deploymentId = processDefinition.getDeploymentId();
        System.out.println("deploymentId = " + deploymentId);
        InputStream bpmnIpt = repositoryService.getResourceAsStream(deploymentId, processDefinition.getResourceName());
        FileOutputStream out = new FileOutputStream(new File("D://myEvection.xml"));
        IOUtils.copy(bpmnIpt, out);
        IOUtils.closeQuietly(bpmnIpt);
        IOUtils.closeQuietly(out);
    }

}
