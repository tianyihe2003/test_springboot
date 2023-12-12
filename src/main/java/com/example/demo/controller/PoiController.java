package com.example.demo.controller;
import com.example.demo.mapper.PoiMapper;
import com.example.demo.pojo.Poi;
import com.example.demo.pojo.Poi_get;
import com.example.demo.vo.PoiVo;
import com.example.demo.vo.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//http主要有四种方法  get，put，post，delete
@CrossOrigin(origins = "http://localhost:8081")
@Slf4j  //引入log日志
@RestController
@RequestMapping("/poi") //请求和下面的类名称可以不一样，但是为了美观，保持一致
public class PoiController {

    @Autowired
    private PoiMapper   poiMapper;

    @GetMapping("/list0")
    public Result list0(@RequestParam(defaultValue="1") int pageSize, int pageNum)
    {
        log.info("my info,pageNumber={}",pageSize);
        var poiVo1 =new PoiVo();
        poiVo1.name="lisi";
        poiVo1.description="He is a male";

        var poiVo2 =new PoiVo();
        poiVo2.name="lisi";
        poiVo2.description="He is a male";

        List poiVos=new ArrayList<PoiVo>();
        poiVos.add(poiVo1);
        poiVos.add(poiVo2);

        return Result.success(poiVos);
    }

   @GetMapping("/list")
    public Result list(@RequestParam(defaultValue="1") Integer pageSize,Integer pageNum)
    {
        log.info("my info,pageNumber={}",pageSize);
        var poiVo =new Poi_get();
        poiVo.pagenum=pageNum;
        poiVo.pagesize=pageSize;
        return Result.success(poiVo);
    }

    @GetMapping("/detail/{id}")  //将参数参数作为地址
    public Result detail(@PathVariable int id)
    {
        log.info("my info,pageNumber={}",id);
        Poi poi=poiMapper.selectById(1);
//        Poi poi=new Poi();
        //poi.name="小米";
        return Result.success(poi);
    }

    @GetMapping("/list3/{id}")              //当返回的是一个类
    public PoiVo list3(@PathVariable int id)  //通过注解  ，框架自动将类转化为json格式返回数据
    {
        var poiVo =new PoiVo();
        poiVo.name="lisi";
        poiVo.description="He is a male";

        log.info("my info,pageNumber={}",id);
        return poiVo;
    }



    @PostMapping("/add")
    public String add(@RequestBody Poi poi)     //新建一个Poi的类，定义一个Poi类的变量,用它来传参
    {                                             //此时可以解析，通过poi.name 来获取变量的值
        log.info("name={}",poi.name);
        return "add the number";
    }



    @PostMapping("/add2")
    public String add2(@RequestBody String name,String description)//通过@RequestBody可以输入json格式的字符串
     {                                                             //但是不会映射到变量
        log.info("name ={}",name);
        log.info("description={}",description);
        return "add the number";
    }

    @PutMapping("/edit")
    public String edit()
    {
        return "add the number";
    }
    @DeleteMapping("/del")
    public String del()
    {
        return "add the number";
    }

    @DeleteMapping("/del2")
    public String del2()
    {
        return "add the number";
    }

}
