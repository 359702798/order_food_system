package com.group.order_food_system.controller;

import com.group.order_food_system.pojo.Orders;
import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.pojo.Store;
import com.group.order_food_system.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//页面跳转    --这个类学习数据返回
//从后台拿到所有的视频信息
//http://localhost:8080/trill/videos/getAll    post和get 携带参数的方式熟不熟悉
@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    //第一种情况，没有参数

    @RequestMapping("/getAll")
    @ResponseBody  //自动将数据转换为json格式的数据并且避免被视图解析器解析
    public Result getAll(){
        //如果要携带数据并且是进行页面跳转？ thymeleaf模板（后面在教学）
        //得到我们视频信息
        List<Store> list = storeService.getAll();
        Result result = new Result();
        result.setCode(0);
        result.setData(list);
        return result;
    }


    //http://localhost:8080/trill/videos/insert?id=17&userId=19&videoDesc=liangzai&videopath=oooo
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(Store video,String stirepath){

        return "";
    }



    @RequestMapping("/deleteById")
    @ResponseBody
    public Result deleteById(String[] ids){
        Result result = storeService.deleteById(ids);
        return result;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Store store){
        Result result = storeService.add(store);
        return result;
    }


    @RequestMapping("/details")
    @ResponseBody
    public Store details(String id){
        Store store = storeService.details(id);
        return store;
    }


    @RequestMapping("/updataOrder")
    @ResponseBody
    public Result updataStore(Store store){
        Result result = storeService.updataStore(store);
        return result;
    }
}
