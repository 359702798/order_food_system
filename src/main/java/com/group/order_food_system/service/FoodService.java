package com.group.order_food_system.service;

import com.group.order_food_system.pojo.Food;
import com.group.order_food_system.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FoodService {

    List<Food> getChinese();

    List<Food> getWest();


    List<Food> getDessert();


    List<Food> getAll();



    Result up(MultipartFile file, HttpServletRequest request);

    Result add(Food food);

    Result deleteById(String[] ids);

    Result updateFoodState(Integer status, String id);

    Food details(String id);

    Result updataFood(Food food);
    Food buy(String id);
}
