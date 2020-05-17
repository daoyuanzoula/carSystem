package com.jkxy.car.api.dao;
import com.jkxy.car.api.pojo.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface FuzzFind {
    @Select("select * from  carMessage  where carName like #{carName}")
    List<Car> fuzzFind(String carName);
}
