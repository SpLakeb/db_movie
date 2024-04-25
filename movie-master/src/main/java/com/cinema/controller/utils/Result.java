package com.cinema.controller.utils;

import lombok.Data;


/**
 * @author 28794
 */
@Data
//统一前后端数据
public class Result {
    private Boolean flag;   //响应状态
    private Object data;    //响应数据
    private String msg;     //响应信息

    public Result(){

    }

    public Result(Boolean flag){
        this.flag= flag;
    }

    public Result(Boolean flag, Object data){
        this.flag=flag;
        this.data=data;
    }

    public Result(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public Result(Boolean flag, String msg, Object data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

}
