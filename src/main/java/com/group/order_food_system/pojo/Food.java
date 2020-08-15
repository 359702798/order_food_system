package com.group.order_food_system.pojo;

import java.io.Serializable;

public class Food implements Serializable {
    private String foodId;

    private String storeId;

    private String foodName;

    private String foodPhoto;

    private Integer foodPrice;

    private String foodType;

    private Integer foodState;

    private static final long serialVersionUID = 1L;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId == null ? null : foodId.trim();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    public String getFoodPhoto() {
        return foodPhoto;
    }

    public void setFoodPhoto(String foodPhoto) {
        this.foodPhoto = foodPhoto == null ? null : foodPhoto.trim();
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType == null ? null : foodType.trim();
    }

    public Integer getFoodState() {
        return foodState;
    }

    public void setFoodState(Integer foodState) {
        this.foodState = foodState;
    }
}