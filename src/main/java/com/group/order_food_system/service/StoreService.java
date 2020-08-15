package com.group.order_food_system.service;
//
//public class StoreService {
//}


import com.group.order_food_system.pojo.Result;
import com.group.order_food_system.pojo.Store;

import java.util.List;

public interface StoreService {


     Result add(Store store);

    List<Store> getAll();



    Result deleteById(String[] ids);

    Store details(String id);

    Result updataStore(Store store);
}