package com.example.demo.vo;

public class Result <T>{
    public int code;
    public String   msg;
    public T    data;
    public static <T>Result success(T data){
        Result r=new Result();
        r.code=0;
        r.msg="success";
        r.data=data;
        return r;
    }
}
