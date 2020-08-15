package com.group.order_food_system.controller;

import com.group.order_food_system.pojo.Food;
import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/Food")
public class FoodController {
    @Autowired
   FoodService foodService;


    @RequestMapping("/getChinese")
    @ResponseBody
    public Result getChinese(){
        Result result=new Result();

        List<Food> list=foodService.getChinese();
        result.setCode(0);
        result.setData(list);
        return result;

    }

    @RequestMapping("/getWest")
    @ResponseBody
    public Result getWest(){
        Result result=new Result();

        List<Food> list=foodService.getWest();
        result.setCode(0);
        result.setData(list);
        return result;

    }

    @RequestMapping("/getDessert")
    @ResponseBody
    public Result getDessert(){
        Result result=new Result();

        List<Food> list=foodService.getDessert();
        result.setCode(0);
        result.setData(list);
        return result;

    }
    @RequestMapping("/buy")
    @ResponseBody
    public Food buy(String id){
        //循环遍历ids

        Food food=foodService.buy(id);

        return  food;

    }

    @RequestMapping("/getAll")
    @ResponseBody
    public Result getAll(){
        Result result=new Result();

        List<Food> list=foodService.getAll();

        result.setCode(0);
        result.setData(list);
        return result;

    }


    @RequestMapping("/updateFoodState")
    @ResponseBody
    public Result updateStatus(Integer status,String id){
        Result result = foodService.updateFoodState(status,id);
        return result;
    }



    @RequestMapping("/deleteById")
    @ResponseBody
    public Result deleteById(String[] ids){
        Result result = foodService.deleteById(ids);
        return result;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Food food){
        Result result = foodService.add(food);
        return result;
    }
    @RequestMapping("/up")
    @ResponseBody
    public Result up(MultipartFile file, HttpServletRequest request){
        Result result = foodService.up(file,request);
        return result;
    }

    @RequestMapping("/details")
    @ResponseBody
    public Food details(String id){
        Food food = foodService.details(id);
        return food;
    }

    @RequestMapping("/updataFood")
    @ResponseBody
    public Result updataFood(Food food){
        Result result = foodService.updataFood(food);
        return result;
    }





}
