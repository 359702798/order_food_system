package com.group.order_food_system.dao;

import com.group.order_food_system.pojo.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Orders record);

    Orders selectByPrimaryKey(String orderId);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);
    List<Orders> selectByUsername(String userName);
    Orders detail(String id);
}