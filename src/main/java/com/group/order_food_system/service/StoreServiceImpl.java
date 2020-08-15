package com.group.order_food_system.service;
//
//public class StoreServiceImpl {
//}


import com.group.order_food_system.dao.StoreMapper;
import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.pojo.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService {
    //在服务层注入Dao层对象   dao和mapper.xml文件肯定被Spring扫描了
    @Autowired
    private StoreMapper storeMapper;
    @Transactional
    @Override
    public  Result add(Store store) {
        // videoPath:video/yq.mp4

        String id = UUID.randomUUID().toString();
        //需要一个user_id--目前先不写
        store.setStoreId(id);


        //视频内容的解析

        //需要视频的时间长度 毫秒/1000

        Result result = new Result();
        try {
            int i = storeMapper.insert(store);
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
    public List<Store> getAll() {
        List<Store> list = storeMapper.selectAll();
        return list;
    }



    //只要是对数据库的数据进行了操作  那么本次操作必须添加事务
    //执行一个过程，要么成功，要么失败
    //处理异常信息的时候  会使用全局异常处理器（类的反射和泛型以及静态变量）
    //当我们在服务层添加了事务以后，如果在服务层又进行了try-catch除了是运行时异常（整个系统停止运行），否则事务不会回滚
    @Transactional
    @Override
    public Result deleteById(String[] ids) {
        Result result = new Result();
        //循环遍历ids   fori
        System.out.println("haha111111111111111");
        try {
            for (int i = 0; i < ids.length; i++) {

                int p = storeMapper.deleteByPrimaryKey(ids[i]);
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
    public Store details(String id) {
        Store store = storeMapper.detail(id);
        return store;
    }

    @Override
    public Result updataStore(Store store) {
        Result result = new Result();

        try {
            int i = storeMapper.updateByPrimaryKey(store);
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



