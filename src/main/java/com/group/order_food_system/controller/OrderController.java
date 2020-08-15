package com.group.order_food_system.controller;

import com.group.order_food_system.pojo.Food;
import com.group.order_food_system.pojo.Orders;
import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.pojo.User;
import com.group.order_food_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Order")
public class OrderController {


    @Autowired
    OrderService orderService;
    @RequestMapping("/add")
    @ResponseBody
    public Result add( Orders orders){
        Result result=orderService.add(orders);
        return  result;


    }
    @RequestMapping("/getUserOrder")
    @ResponseBody
    public Result getUserOrder(HttpServletRequest request){
        Result result=new Result();

        User user= (User) request.getSession().getAttribute("user");
        System.out.println(user.getUserName());
        List<Orders> list=orderService.selectByUsername(user.getUserName());
        result.setCode(0);
        result.setData(list);
        return  result;


    }
    @RequestMapping("/getAll")
    @ResponseBody
    public Result getAll(){
        List<Orders> list = orderService.getAll();
        Result result = new Result();
        result.setCode(0);
        result.setData(list);
        return result;
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public Result deleteById(String[] ids){
        Result result = orderService.deleteById(ids);
        return result;
    }

    @RequestMapping("/details")
    @ResponseBody
    public Orders details(String id){
        Orders order = orderService.details(id);
        return order;
    }



    @RequestMapping("/updataOrder")
    @ResponseBody
    public Result updataOrder(Orders orders){
        Result result = orderService.updataOrder(orders);
        return result;
    }


}
