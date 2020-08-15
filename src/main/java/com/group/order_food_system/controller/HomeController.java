package com.group.order_food_system.controller;

import cn.dsna.util.images.ValidateCode;
import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.service.AdminService;
import com.group.order_food_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/index")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @RequestMapping("/main")
    public String main() {
        System.out.println("哈哈，我马上跳转了");
        return "login";


    }
    @RequestMapping("/usermain")
    public String usermain() {
        System.out.println("哈哈，登录成功了!");
        return "usermain";


    }
    @RequestMapping("/adminmain")
    public String adminmain() {
        System.out.println("哈哈，登录成功了!");
        return "adminmain";


    }
    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username,String password,String code,HttpServletRequest request ){
    Result result=userService.login(username,password,code,request);

        return  result;
    }
    @RequestMapping("/adminlogin")
    @ResponseBody
    public Result adminlogin(String adminname,String adminpassword,String code,HttpServletRequest request ){
        Result result=adminService.adminlogin(adminname,adminpassword,code,request);


        return  result;
    }




    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response,HttpServletRequest request) {
ValidateCode validateCode=new ValidateCode(120,40,4,50);

   //在后台设置一个session在存放当前的验证码
request.getSession().setAttribute("code",validateCode.getCode());

    try {
        //将验证码图片以字节的方式返回给前端
        validateCode.write(response.getOutputStream());
    }catch (IOException e){
  e.printStackTrace();
    }


    }
}