package com.zolve.test.foodorderworkflow.service;

import com.zolve.test.foodorderworkflow.entity.BuildCartRequest;
import lombok.RequiredArgsConstructor;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderCartService {

    private final ProcessEngineService processEngineService;

    public void applyOffer(String offerCode){
        Task applyOfferTask = processEngineService.getTask("Apply Offer");
        if(applyOfferTask==null)
            return;
        Map<String,Object> variables = new HashMap<>();
        if(offerCode!=null){
            variables.put("applyOffer",true);
            variables.put("offerCode",offerCode);
            System.out.println("Offer applied "+ offerCode);
        }
        else{
            variables.put("applyOffer",false);
            System.out.println("Offer not applied");
        }
        processEngineService.completeTask(applyOfferTask.getId(),variables);
    }

    public void updateItem(int itemCount){
        Task updateItemTask = processEngineService.getTask("Update Item");
        if(updateItemTask==null)
            return;
        Map<String,Object> variables = new HashMap<>();
        variables.put("itemCount",itemCount);
        processEngineService.completeTask(updateItemTask.getId(),variables);
    }

    public void buildCart(BuildCartRequest buildCartRequest){
        Map<String,Object> variables = new HashMap<>();
        variables.put("restaurantName",buildCartRequest.getRestaurantName());
        variables.put("restaurantLocation",buildCartRequest.getRestaurantLocation());
        variables.put("itemCount",buildCartRequest.getItemCount());
        processEngineService.createProcessInstance(variables);
    }

    public void setDeliveryLocation(String deliveryLocation){
        Task addDeliveryLocationTask = processEngineService.getTask("Add delivery location");
        if(addDeliveryLocationTask==null){
            return;
        }
        Map<String,Object> variables = new HashMap<>();
        variables.put("deliveryLocation",deliveryLocation);
        processEngineService.completeTask(addDeliveryLocationTask.getId(),variables);
    }


}
