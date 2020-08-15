package com.group.order_food_system.service;

import com.group.order_food_system.dao.UserMapper;
import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.pojo.User;
import com.group.order_food_system.utill.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
 @Autowired
 private UserMapper userMapper;

    @Override
    public Result login(String username, String password, String code, HttpServletRequest request) {
       Result result=new Result();
       //先验证验证码是非正确
      String codeValue=(String) request.getSession().getAttribute("code");
    if(codeValue.equalsIgnoreCase(code)){
        User user=userMapper.selectByUsername(username);
if(user==null||!user.getUserPassword().equals(password)){
  result.setCode(500);
  result.setMsg("用户名或密码错误");

}else{
    //执行成功
  result.setMsg("success");
  result.setCode(200);
 request.getSession().setAttribute("user",user);

}


}else{
    result.setCode(500);
    result.setMsg("验证码错误!");



}
return  result;




    }

    @Transactional
    @Override
    public Result add(User user) {
        Result result = new Result();
        try {
            int i = userMapper.insert(user);
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
    public List<User> getAll() {
        List<User> list = userMapper.selectAll();
        return list;
    }



    //只要是对数据库的数据进行了操作  那么本次操作必须添加事务
    //执行一个过程，要么成功，要么失败
    //处理异常信息的时候  会使用全局异常处理器（类的反射和泛型以及静态变量）
    //当我们在服务层添加了事务以后，如果在服务层又进行了try-catch除了是运行时异常（整个系统停止运行），否则事务不会回滚
    @Transactional
    @Override
    public Result deleteById(Integer[] ids) {
        Result result = new Result();
        //循环遍历ids   fori
        System.out.println("haha111111111111111");
        try {
            for (int i = 0; i < ids.length; i++) {

                int p = userMapper.deleteByPrimaryKey(ids[i]);
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
    public User details(String id) {
        User user = userMapper.detail(id);
        return user;
    }

    @Override
    public Result updataUser(User user) {
        Result result = new Result();
        try {
            int i = userMapper.updateByPrimaryKey(user);
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


}
