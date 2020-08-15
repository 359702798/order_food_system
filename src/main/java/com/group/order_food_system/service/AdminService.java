package com.group.order_food_system.service;

import com.group.order_food_system.pojo.Admin;
import com.group.order_food_system.pojo.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminService {

    Result adminlogin(String adminname, String adminpassword, String code, HttpServletRequest request);

    Result add(Admin admin);

    List<Admin> getAll();



    Result deleteById(Integer[] ids);

    Admin details(String id);

    Result updataAdmin(Admin admin);


}
