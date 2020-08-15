package com.group.order_food_system.dao;

import com.group.order_food_system.pojo.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodMapper {
    int deleteByPrimaryKey(String foodId);

    int insert(Food record);

    Food selectByPrimaryKey(String foodId);

    List<Food> selectAll();

    int updateByPrimaryKey(Food record);

    List<Food> selectChinese();

    List<Food> selectWest();

    List<Food> selectDessert();

    Food buy(String id);

    int updateFoodState(@Param("status")Integer status, @Param("id")String id);

    Food detail(String id);
}