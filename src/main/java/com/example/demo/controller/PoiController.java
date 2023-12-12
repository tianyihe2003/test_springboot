package com.example.demo.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.PoiMapper;
import com.example.demo.pojo.Poi;
import com.example.demo.pojo.Poi_get;
import com.example.demo.service.IPoiService;
import com.example.demo.vo.PoiVo;
import com.example.demo.vo.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//http主要有四种方法  get，put，post，delete
@CrossOrigin(origins = "http://localhost:8081")
@Slf4j  //引入log日志
@RestController
@RequestMapping("/poi") //请求和下面的类名称可以不一样，但是为了美观，保持一致
public class PoiController {

    @Autowired
    private IPoiService poiService;

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
        Page<Poi>  page=new Page<Poi>(pageSize,pageNum);
        IPage<Poi> pageResult =poiService.page(page);//pageResult里面包含多余的所有的数据库信息，要对它进行处理
        List<Poi> poiList=pageResult.getRecords();//recoeds上面pageresult获取的列表


//        List voList=new ArrayList() ;
//        for (Poi poi:poiList){  //将数据库获取的poi转换成poiVo结构
//            PoiVo poiVo=new PoiVo();
////            poiVo.id=poi.id;
////            poiVo.name=poi.name;
////            poi.description=poi.description;
//
//            BeanUtils.copyProperties(poi,poiVo);//springboot提供的方法快速把poi（作为源），里面的数据赋值到poivo（目的地）
//                                                //只有两个类名字相同的变量值才会赋值
//                                                //这里要设置Poi和PoiVo为setting and getting，到Poi类里面右键
//                                                //generate ->getting and setting ->全选、生成
//                                                //也可以在类前面加上@Data注解，让编译器在编译的时候加上，让代码中不出现，编译时加上
//            voList.add(poiVo);
//        }

        //上面的代码用箭头函数代替
        List voList=pageResult.getRecords().stream().map(poi ->
        {
            PoiVo poiVo=new PoiVo();
            BeanUtils.copyProperties(poi,poiVo);
            return poiVo;
        }).collect(Collectors.toList());
        pageResult.setRecords(voList);//把我们生成的poList替代recordlist
        return Result.success(pageResult);
    }

    @GetMapping("/detail/{id}")  //将参数参数作为地址
    public Result detail(@PathVariable int id)
    {
        log.info("my info,pageNumber={}",id);
        Poi poi=poiService.getById(id);
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
    public Result add(@RequestBody Poi poi)     //新建一个Poi的类，定义一个Poi类的变量,用它来传参
    {                                             //此时可以解析，通过poi.name 来获取变量的值
        log.info("name={}",poi);
        poiService.save(poi);//这里上传的同时会给poi赋值id
        PoiVo poiVo=new PoiVo();
        BeanUtils.copyProperties(poi,poiVo);

        return Result.success(poiVo);
    }



    @PostMapping("/add2")
    public String add2(@RequestBody String name,String description)//通过@RequestBody可以输入json格式的字符串
     {                                                             //但是不会映射到变量
        log.info("name ={}",name);
        log.info("description={}",description);
        return "add the number";
    }

    @PutMapping("/edit/{id}")
    public Result edit(@RequestBody  Poi poi,@PathVariable Integer id)
    {
        log.info("name{}",poi);
        poi.setId(id);
        poiService.updateById(poi);
        return Result.success(poi);
    }
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable Integer id)
    {
        log.info("id={}",id);
        poiService.removeById(id);
        return Result.success(1);
    }

    @DeleteMapping("/del2")
    public String del2()
    {
        return "add the number";
    }

}
