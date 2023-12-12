package com.example.demo.pojo;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName("poi_table")
public class Poi {
   public String name;
   public Integer id;
   public Float  lng;
   public Float   lot;
   public String  coverUrl;//在数据库里面的下划线后的下一个字母大写对应于驼峰命名
   public  String description;
}


