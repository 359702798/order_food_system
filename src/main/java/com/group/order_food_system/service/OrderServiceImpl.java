package com.group.order_food_system.service;

import com.group.order_food_system.dao.OrdersMapper;
import com.group.order_food_system.pojo.Orders;
import com.group.order_food_system.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements  OrderService {
    @Autowired
    OrdersMapper ordersMapper;

   @Transactional
    @Override
    public Result add(Orders orders) {
       int  count=orders.getOrderCount();
       BigDecimal bcount=BigDecimal.valueOf((int)count);
       BigDecimal price=orders.getFoodPrice();


       BigDecimal totalPrice=price.multiply(bcount);
       orders.setTotalPrice(totalPrice);
      String id= UUID.randomUUID().toString();
       orders.setOrderId(id);
        Date date=new Date();
        orders.setOrderTime(date);
        Result result=new Result();
   try {
       int i =  ordersMapper.insert(orders);
       if(i>0){
           result.setCode(200);
       }else {
           result.setCode(500);
       }
       return  result;
   }catch (Exception e){

       e.printStackTrace();
       TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
       result.setCode(500);
       return  result;

   }


    }

    @Override
    public List<Orders> selectByUsername(String userName) {
       System.out.println(userName+"我的");
        List<Orders> list=ordersMapper.selectByUsername(userName);
        return list;
    }
    @Override
    public List<Orders> getAll() {
        List<Orders> list = ordersMapper.selectAll();
        return list;
    }

    @Transactional     //数据库的更新，需要添加事务
    @Override
    public Result deleteById(String[] ids) {
        Result result = new Result();
        //循环遍历
        try {
            for (int i = 0; i < ids.length; i++) {
                int p = ordersMapper.deleteByPrimaryKey(ids[i]);
            }
            result.setMsg("SUCCESS");
            result.setCode(200);
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("系统繁忙，请刷新页面再进行尝试！");
            result.setCode(500);
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }

    }

    @Override
    public Orders details(String id) {
        Orders orders = ordersMapper.detail(id);
        return orders;
    }

    @Transactional
    @Override
    public Result updataOrder(Orders order) {
        Result result = new Result();
        Date date=new Date();
        order.setOrderTime(date);
        try {
            int i = ordersMapper.updateByPrimaryKey(order);
            if (i>0){
                result.setMsg("SUCCESS");
                result.setCode(200);
                System.out.println(result.getCode());
            }else {
                result.setCode(500);

            }
            return result;

        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("系统繁忙，请刷新页面再进行尝试！");
            result.setCode(500);
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }

    }

}
