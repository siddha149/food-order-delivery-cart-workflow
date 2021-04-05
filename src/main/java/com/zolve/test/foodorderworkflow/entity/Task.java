package com.zolve.test.foodorderworkflow.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Task {
    private String taskId;
    private String taskName;
    private String processInstanceId;
}
