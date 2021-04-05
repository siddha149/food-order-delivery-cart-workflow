package com.zolve.test.foodorderworkflow.controller;

import com.zolve.test.foodorderworkflow.entity.Task;
import com.zolve.test.foodorderworkflow.service.ProcessEngineService;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/process-engine/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProcessEngineController {

    private final ProcessEngineService processEngineService;

//    public ProcessEngineController() {
//        this.processEngineService = new ProcessEngineService();
//    }

    @GetMapping(path = "/tasks")
    public ResponseEntity<List<Task>> getTasks(){
        return new ResponseEntity<>(processEngineService.getTasks(), HttpStatus.OK);
    }

    @PostMapping(path = "/process-instance")
    public ResponseEntity<String> createProcessInstance(){
        return new ResponseEntity<>(processEngineService.createProcessInstance().getId(), HttpStatus.OK);
    }

    @PostMapping(path="/complete-task/{task-id}")
    public ResponseEntity completeTask(@PathVariable("task-id") String taskId){
        processEngineService.completeTask(taskId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
