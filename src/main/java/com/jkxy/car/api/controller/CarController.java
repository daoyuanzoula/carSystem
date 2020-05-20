package com.jkxy.car.api.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        //System.out.println("111111111111111111111111111111111");
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    /**
     * 买车
     *
     * @return
     */
    @PostMapping("buyCar")
    public JSONResult buyCar(@RequestParam int id, @RequestParam int buyNum ) {
        Car car = carService.findById(id);
        if (car.getNum() > 0 && car.getNum() > buyNum){
            carService.buyCar(car);
            return JSONResult.ok();
        } else {
            System.out.println("11111111111111111");
            return JSONResult.nok();
        }

    }

    /**
     * 模糊查询
     *
     * @return
     */
    @PostMapping("fuzzFind")
    public JSONResult fuzzFind(@RequestParam String carName,@RequestParam int pageNum,@RequestParam int pageSize) {
        carName="%"+carName+"%";
        PageHelper.startPage(pageNum, pageSize);
        List<Car> cars = carService.fuzzFind(carName);
        PageInfo info = new PageInfo(cars);
        return JSONResult.ok(info);

    }
}
