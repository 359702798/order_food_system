package com.group.order_food_system.service;

import com.group.order_food_system.dao.FoodMapper;
import com.group.order_food_system.pojo.Food;
import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.utill.UpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
public class FoodServiceImpl implements FoodService {
    //在服务层注入dao对象
    @Autowired

    private FoodMapper foodMapper;

    @Override
    public List<Food> getChinese() {
        List<Food> list=foodMapper.selectChinese();

        return list;
    }

    @Override
    public List<Food> getWest() {
        List<Food> list=foodMapper.selectWest();
        return list;
    }

    @Override
    public List<Food> getDessert() {
        List<Food> list=foodMapper.selectDessert();
        return list;
    }

    @Override
    public Food buy(String id) {
        Food food=foodMapper.buy(id);

        return  food;

    }


    @Override
    public List<Food> getAll() {
        List<Food> list = foodMapper.selectAll();
        return list;
    }





    @Transactional
    @Override
    public Result deleteById(String[] ids) {
        Result result = new Result();
        //循环遍历ids   fori
        System.out.println("haha111111111111111");
        try {
            for (int i = 0; i < ids.length; i++) {

                int p = foodMapper.deleteByPrimaryKey(ids[i]);
            }
            result.setMsg("SUCCESS");
            result.setCode(200);
            return result;
        }catch (Exception e){
            //在控制台打印输出错误信息
            e.printStackTrace();
            result.setMsg("系统繁忙，请刷新页面在进行尝试！");
            result.setCode(500);
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }
    }

    @Override
    public Result updateFoodState(Integer status, String id) {
        int i = foodMapper.updateFoodState(status,id);
        Result result = new Result();
        if (i>0){
            result.setMsg("SUCCESS");
        }else {
            result.setMsg("ERROR");
        }
        return result;
    }

    @Override
    public Food details(String id) {
        Food food = foodMapper.detail(id);
        return food;
    }

    @Override
    public Result updataFood(Food food) {
        Result result = new Result();
        try {
            int i = foodMapper.updateByPrimaryKey(food);
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

    @Transactional
    @Override
    public  Result add(Food food) {
        // videoPath:video/yq.mp4

        String id = UUID.randomUUID().toString();
        //需要一个user_id--目前先不写
        food.setFoodId(id);
        food.setFoodState(1);
        food.setFoodPhoto("img/"+food.getFoodPhoto());
//        food.setFoodPhoto("food/"+foodPhoto);


        //视频内容的解析

        //需要视频的时间长度 毫秒/1000

        Result result = new Result();
        try {
            int i = foodMapper.insert(food);
            if(i>0){
                result.setCode(200);
            }else{
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
    public Result up(MultipartFile file, HttpServletRequest request) {
        //进行文件的上传操作
        String foodPhoto = UpUtils.upfile(file, request);
        Result result = new Result();
        result.setMsg(foodPhoto);
        return result;
    }
}
