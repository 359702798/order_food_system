package com.group.order_food_system.controller;


import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.pojo.Store;
import com.group.order_food_system.pojo.User;
import com.group.order_food_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/add")
    @ResponseBody
    public Result add(User user) {
        Result result = userService.add(user);
        return result;


    }

    @RequestMapping("/getAll")
    @ResponseBody  //自动将数据转换为json格式的数据并且避免被视图解析器解析
    public Result getAll(){
        //如果要携带数据并且是进行页面跳转？ thymeleaf模板（后面在教学）
        //得到我们视频信息
        List<User> list = userService.getAll();
        Result result = new Result();
        result.setCode(0);
        result.setData(list);
        return result;
    }


    //http://localhost:8080/trill/videos/insert?id=17&userId=19&videoDesc=liangzai&videopath=oooo
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(Store video, String stirepath){

        return "";
    }



    @RequestMapping("/deleteById")
    @ResponseBody
    public Result deleteById(Integer[] ids){
        Result result = userService.deleteById(ids);
        return result;
    }


    @RequestMapping("/details")
    @ResponseBody
    public User details(String id){
        User user = userService.details(id);
        return user;
    }


    @RequestMapping("/updataUser")
    @ResponseBody
    public Result updataUser(User user){
        Result result = userService.updataUser(user);
        return result;
    }
}
