package com.woniu.service;

import java.util.List;

import com.woniu.domain.Reduceprice;

public interface IReducepriceService {
    void save (Reduceprice reduceprice);
    void delete(Integer rid);
    void update(Reduceprice reduceprice);
    
    //上下架
    void upAndDown(Integer rid);
    
    Reduceprice select(Integer rid);
    List<Reduceprice> select();
    
    //模糊查询
    List<Reduceprice> select(String name);
}
