package com.zolve.test.foodorderworkflow.service;

import com.zolve.test.foodorderworkflow.entity.Task;
import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProcessEngineService {

    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final TaskService taskService;
    private final ProcessDefinition processDefinition;

    public ProcessEngineService() {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setAsyncExecutorActivate(true);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        this.repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("order_cart.bpmn20.xml")
                .deploy();

        this.processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        this.runtimeService = processEngine.getRuntimeService();
        this.taskService = processEngine.getTaskService();
    }

    public List<Task> getTasks(){
        List<org.flowable.task.api.Task> tasks = taskService.createTaskQuery().list();
        return tasks.stream().map(task -> Task.builder()
                .taskId(task.getId())
                .taskName(task.getName())
                .processInstanceId(task.getProcessInstanceId()).build()).collect(Collectors.toList());
    }

    public ProcessInstance createProcessInstance(){
        return runtimeService.startProcessInstanceById(processDefinition.getId());
    }

    public ProcessInstance createProcessInstance(Map<String,Object> variables){
        return runtimeService.startProcessInstanceById(processDefinition.getId(),variables);
    }

    public void completeTask(String taskId){
        Map<String,Object> variables = new HashMap<>();
        variables.put("itemcount",0);
        variables.put("applyoffer",true);
        taskService.complete(taskId,variables);
    }

    public void completeTask(String taskId,Map<String,Object> variables){
        taskService.complete(taskId,variables);
    }

    public org.flowable.task.api.Task getTask(String taskName){
        return taskService.createTaskQuery().taskName(taskName).singleResult();
    }
}
