package com.zolve.test.foodorderworkflow;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.boot.SpringApplication;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class FoodOrderWorkflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderWorkflowApplication.class, args);

	}

}
