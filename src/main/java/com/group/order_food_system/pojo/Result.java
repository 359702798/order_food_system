package com.group.order_food_system.pojo;

import java.util.List;

/*专门封装返回的数据信息（来自table数据格式需求）*/
public class Result {
    //code默认成功情况为 0
    private Integer code;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private  String msg;

    private  Long count;

    private List data;


}
