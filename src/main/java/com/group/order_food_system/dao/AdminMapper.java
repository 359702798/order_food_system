package com.group.order_food_system.dao;

import com.group.order_food_system.pojo.Admin;
import com.group.order_food_system.pojo.User;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);


    Admin selectByadminname(String adminname);




    Admin detail(String id);
}