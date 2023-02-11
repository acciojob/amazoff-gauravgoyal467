package com.driver;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String,Order> OrderDb=new HashMap<>();

    HashMap<String,DeliveryPartner> DeliveryPartnerDb=new HashMap<>();

    HashMap<String,List<String>> PartnerOrderDb=new HashMap<>();

    HashSet<String>assignedOrderset=new HashSet<>();

    HashSet<String>unAssignedOrderset=new HashSet<>();



    //1
    public String addOrder(Order order){
        OrderDb.put(order.getId(),order);
        unAssignedOrderset.add(order.getId());
        return "New order added successfully" ;
    }

    //2
    public String addPartner(String partnerId){
        DeliveryPartner dp=new DeliveryPartner(partnerId);
        DeliveryPartnerDb.put(partnerId,dp);
        return "New delivery partner added successfully" ;
    }

    //3
    public String addOrderPartnerPair(String orderId,String partnerId){
        DeliveryPartner delp=DeliveryPartnerDb.get(partnerId);
        int count=delp.getNumberOfOrders();
        count++;
        delp.setNumberOfOrders(count);
        DeliveryPartnerDb.put(partnerId,delp);

        if(PartnerOrderDb.containsKey(partnerId)){
            List<String>current =PartnerOrderDb.get(partnerId);
            current.add(orderId);
            PartnerOrderDb.put(partnerId,current);
        }else{
            List<String>current =new ArrayList<>();
            current.add(orderId);
            PartnerOrderDb.put(partnerId,current);
        }
        assignedOrderset.add(orderId);

        if(unAssignedOrderset.contains(orderId)){
            unAssignedOrderset.remove(orderId);
        }
        return "New order-partner pair added successfully";
    }

    //4
    public Order getOrderById( String orderId){
        return OrderDb.get(orderId);
    }

    //5
    public DeliveryPartner getPartnerById(String partnerId){
        DeliveryPartner deliveryPartner = DeliveryPartnerDb.get(partnerId);
        return deliveryPartner;
    }

    //6
    public Integer getOrderCountByPartnerId(String partnerId){
        return DeliveryPartnerDb.get(partnerId).getNumberOfOrders();
    }

    //7`
    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> orders = PartnerOrderDb.get(partnerId);
        //orders should contain a list of orders by PartnerId

        return orders;
    }

    //8
    public List<String> getAllOrders(){
        List<String> orders = new ArrayList<>();
        for(String x: unAssignedOrderset){
            orders.add(x);
        }
        for(String x: assignedOrderset){
            orders.add(x);
        }
        return orders;
    }

    //9
    public Integer getCountOfUnassignedOrders(){
       /* Integer countOfOrders = 0;
        for(String x:OrderDb.keySet()){
            if(!assignedOrderset.contains(x)){
                countOfOrders++;
            }
        }*/
       return unAssignedOrderset.size();
    }

    //10
    public Integer getOrdersLeftAfterGivenTimeByPartnerId( String time,String partnerId){
        String[]arr= time.split(":");
        int dTime=(Integer.valueOf(arr[0])*60)+Integer.valueOf(arr[1]);
        int countOfOrders = 0;
        List<String>orders=PartnerOrderDb.get(partnerId);
        for(String x:orders){
            if(OrderDb.get(x).getDeliveryTime()>dTime){
                countOfOrders++;
            }
        }
        return countOfOrders;
    }

    //11
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        List<String>orders=PartnerOrderDb.get(partnerId);
        int max=0;
        for(String x:orders){
            if(OrderDb.get(x).getDeliveryTime()>max){
                max=OrderDb.get(x).getDeliveryTime();
            }
        }
        int minutes=max%60;
        max=max-minutes;
        int hours=max/60;

        String time=String.valueOf(hours)+":"+String.valueOf(minutes);
        return time;
    }

    //12
    public String deletePartnerById(String partnerId){
        List<String> curr=PartnerOrderDb.get(partnerId);
        PartnerOrderDb.remove(partnerId);
        DeliveryPartnerDb.remove(partnerId);
        //And push all his assigned orders to unassigned orders.

        for(String x:curr){
            assignedOrderset.remove(x);
            unAssignedOrderset.add(x);
        }
        return " removed successfully";
    }

    //13
    public String deleteOrderById(String orderId){
        OrderDb.remove(orderId);
        for(String x:PartnerOrderDb.keySet()){
            List<String>curr=PartnerOrderDb.get(x);
            for(String y:curr){
                if(y.equals(orderId)){
                    curr.remove(orderId);
                    PartnerOrderDb.put(x,curr);
                    break;
                }
            }
        }
        if(assignedOrderset.contains(orderId)){
            assignedOrderset.remove(orderId);
        }
        if(unAssignedOrderset.contains(orderId)){
            unAssignedOrderset.remove(orderId);
        }
        //Delete an order and also
        // remove it from the assigned order of that partnerId
        return " removed successfully";
    }
}
