package com.zolve.test.foodorderworkflow.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuildCartRequest {
    private String restaurantName;
    private String restaurantLocation;
    private int itemCount;
}
