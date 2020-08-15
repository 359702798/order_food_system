package com.group.order_food_system.dao;

import com.group.order_food_system.pojo.Store;
import java.util.List;

public interface StoreMapper {
    int deleteByPrimaryKey(String storeId);

    int insert(Store record);

    Store selectByPrimaryKey(String storeId);

    List<Store> selectAll();

    int updateByPrimaryKey(Store record);
    Store detail(String id);
}