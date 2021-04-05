package com.zolve.test.foodorderworkflow.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class DeliveryLocationValidationDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        String deliveryLocation = (String) execution.getVariable("deliveryLocation");
        String restaurantLocation = (String) execution.getVariable("restaurantLocation");

        if(deliveryLocation.equals(restaurantLocation)){
            execution.setTransientVariable("deliverable",true);
        }
        else{
            execution.setTransientVariable("deliverable",false);
        }
    }
}
