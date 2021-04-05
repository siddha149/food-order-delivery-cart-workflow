package com.zolve.test.foodorderworkflow.controller;

import com.zolve.test.foodorderworkflow.entity.BuildCartRequest;
import com.zolve.test.foodorderworkflow.service.OrderCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/order-cart/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderCartController {

    private final OrderCartService orderCartService;

    @PostMapping(path = "/apply-offer/")
    public ResponseEntity applyOffer(@RequestParam(required = false) String offerCode){
        orderCartService.applyOffer(offerCode);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/update-item/")
    public ResponseEntity updateItem(@RequestParam Integer itemCount){
        orderCartService.updateItem(itemCount);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/build-cart/")
    public ResponseEntity buildCart(@RequestBody BuildCartRequest buildCartRequest){
        orderCartService.buildCart(buildCartRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/set-delivery-location/")
    public ResponseEntity setDeliveryLocation(@RequestParam String deliveryLocation){
        orderCartService.setDeliveryLocation(deliveryLocation);
        return new ResponseEntity(HttpStatus.OK);
    }
}
