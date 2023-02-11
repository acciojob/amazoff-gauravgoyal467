package com.driver;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {


OrderService orderService=new OrderService();

    //1
    @PostMapping("/add-order")
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        String message=orderService.addOrder(order);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //2
    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<String> addPartner(@PathVariable String partnerId){
        String message=orderService.addPartner(partnerId);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //3
    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPartnerPair(@RequestParam String orderId, @RequestParam String partnerId){
        String message=orderService.addOrderPartnerPair(orderId,partnerId);
        //This is basically assigning that order to that partnerId
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //4
    @GetMapping("/get-order-by-id/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId){
        Order order=orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    //5
    @GetMapping("/get-partner-by-id/{partnerId}")
    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerId){
        DeliveryPartner deliveryPartner=orderService.getPartnerById(partnerId);
        //deliveryPartner should contain the value given by partnerId

        return new ResponseEntity<>(deliveryPartner, HttpStatus.CREATED);
    }

    //6
    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable String partnerId){

        Integer orderCount = 0;
        orderCount=orderService.getOrderCountByPartnerId(partnerId);
        //orderCount should denote the orders given by a partner-id

        return new ResponseEntity<>(orderCount, HttpStatus.CREATED);
    }

    //7
    @GetMapping("/get-orders-by-partner-id/{partnerId}")
    public ResponseEntity<List<String>> getOrdersByPartnerId(@PathVariable String partnerId){
        List<String> orders = orderService.getOrdersByPartnerId(partnerId);
        //orders should contain a list of orders by PartnerId

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    //8
    @GetMapping("/get-all-orders")
    public ResponseEntity<List<String>> getAllOrders(){
        List<String> orders = orderService.getAllOrders();
        //Get all orders
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    //9
    @GetMapping("/get-count-of-unassigned-orders")
    public ResponseEntity<Integer> getCountOfUnassignedOrders(){
        Integer countOfOrders = orderService.getCountOfUnassignedOrders();
        //Count of orders that have not been assigned to any DeliveryPartner

        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }

    //10
    @GetMapping("/get-count-of-orders-left-after-given-time/{partnerId}")
    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId){

        Integer countOfOrders = orderService.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
        //countOfOrders that are left after a particular time of a DeliveryPartner

        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }

    //11
    @GetMapping("/get-last-delivery-time/{partnerId}")
    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId){
        String time = orderService.getLastDeliveryTimeByPartnerId(partnerId);
        //Return the time when that partnerId will deliver his last delivery order.

        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }

    //12
    @DeleteMapping("/delete-partner-by-id/{partnerId}")
    public ResponseEntity<String> deletePartnerById(@PathVariable String partnerId){
        String message =orderService.deletePartnerById(partnerId);
        //Delete the partnerId
        //And push all his assigned orders to unassigned orders.

        return new ResponseEntity<>(partnerId +message, HttpStatus.CREATED);
    }

    //13
    @DeleteMapping("/delete-order-by-id/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String orderId){
        String message =orderService.deleteOrderById(orderId);
        //Delete an order and also
        // remove it from the assigned order of that partnerId

        return new ResponseEntity<>(orderId +message, HttpStatus.CREATED);
    }
}
