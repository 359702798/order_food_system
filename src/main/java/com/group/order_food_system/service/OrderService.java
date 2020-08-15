package com.group.order_food_system.service;

import com.group.order_food_system.pojo.Orders;
import com.group.order_food_system.pojo.Result;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderService {

    List<Orders> getAll();

    Result deleteById(String[] ids);

    Orders details(String id);

    Result updataOrder(Orders order);

    Result add(Orders orders);


    List<Orders> selectByUsername(String userName);
}
