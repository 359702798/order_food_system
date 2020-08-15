package com.group.order_food_system.service;

import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    Result login(String username, String password, String code, HttpServletRequest request);


    Result add(User user);

    List<User> getAll();



    Result deleteById(Integer[] ids);


    User details(String id);

    Result updataUser(User user);
}
