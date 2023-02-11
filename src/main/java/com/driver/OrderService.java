package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository=new OrderRepository();

    //1
    public String addOrder(Order order){
        return orderRepository.addOrder(order);
    }

    //2
    public String addPartner(String partnerId){
        return orderRepository.addPartner(partnerId);
    }

    //3
    public String addOrderPartnerPair(String orderId,String partnerId){
        return orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    //4
    public Order getOrderById(String orderId){
        return orderRepository.getOrderById(orderId);
    }

    //5
    public DeliveryPartner getPartnerById(String partnerId){
        return orderRepository.getPartnerById(partnerId);
    }

    //6
    public Integer getOrderCountByPartnerId(String partnerId){
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    //7
    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    //8
    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    //9
    public Integer getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }

    //10
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    //11
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    //12
    public String deletePartnerById(String partnerId){
        return orderRepository.deletePartnerById(partnerId);
    }

    //13
    public String deleteOrderById(String orderId){
        return orderRepository.deleteOrderById(orderId);
    }
}
