package com.zolve.test.foodorderworkflow.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidateOfferDelegate implements JavaDelegate {

    private Set<String> validOfferCodes = new HashSet<>(Arrays.asList("SWIGGYIT","WOW100"));

    @Override
    public void execute(DelegateExecution execution) {
        String offerCode = (String) execution.getVariable("offerCode");
        if(validOfferCodes.contains(offerCode)){
            execution.setTransientVariable("validOffer",true);
            System.out.println("Valid Offer Code");
        }
        else{
            execution.setTransientVariable("validOffer",false);
            System.out.println("Invalid Offer Code");
        }

    }
}
