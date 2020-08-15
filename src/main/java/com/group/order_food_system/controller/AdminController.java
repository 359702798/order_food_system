package com.group.order_food_system.controller;


import com.group.order_food_system.pojo.Admin;
import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.pojo.Store;
import com.group.order_food_system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //第一种情况，没有参数

    @RequestMapping("/getAll")
    @ResponseBody  //自动将数据转换为json格式的数据并且避免被视图解析器解析
    public Result getAll(){
        //如果要携带数据并且是进行页面跳转？ thymeleaf模板（后面在教学）
        //得到我们视频信息
        List<Admin> list = adminService.getAll();
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
        Result result = adminService.deleteById(ids);
        return result;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Admin admin){
        Result result = adminService.add(admin);
        return result;
    }

    @RequestMapping("/details")
    @ResponseBody
    public Admin details(String id){
        Admin admin = adminService.details(id);
        return admin;
    }

    @RequestMapping("/updataAdmin")
    @ResponseBody
    public Result updataAdmin(Admin admin){
        Result result = adminService.updataAdmin(admin);
        return result;
    }

}
