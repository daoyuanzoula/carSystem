package com.jkxy.car.api.dao;

import com.jkxy.car.api.pojo.Car;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BuyCar {
    @Update("update carMessage set carName=#{carName},carType=#{carType},price=#{price},carSeries=#{carSeries},num=#{num}-1 where id = #{id}")

    void buyCar(Car car);
}
