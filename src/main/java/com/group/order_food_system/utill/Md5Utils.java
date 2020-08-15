package com.group.order_food_system.utill;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.security.PublicKey;

public class Md5Utils {
    public  static  String encryption(String username,String password){
// 使用shiro 提供的加密算法
        // 第一个参数 加密对象   //第二个参数  盐值  // 第三个  加密次数
    Md5Hash md5Hash=new Md5Hash(password,username,1000);
    return  md5Hash.toString();

    }

    public static void main(String[] args) {
        System.out.println(encryption("liangzai","123"));
    }
}
