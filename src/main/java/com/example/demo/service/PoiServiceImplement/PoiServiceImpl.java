package com.example.demo.service.PoiServiceImplement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.PoiMapper;
import com.example.demo.pojo.Poi;
import com.example.demo.service.IPoiService;
import org.springframework.stereotype.Service;

@Service
public class PoiServiceImpl extends ServiceImpl<PoiMapper, Poi> implements IPoiService {
}
