package com.ontherun.restful_api.controller;

import com.ontherun.restful_api.domain.Car;
import com.ontherun.restful_api.service.CarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users/{userId}/car")
public class CarController {

    @Resource
    private CarService carService;

    /**
     * Get User's car
     * @param userId userId
     * @return Car
     */
    @RequestMapping(method = RequestMethod.GET)
    public Car getCar(@PathVariable Long userId) {
        return carService.getCar(userId);
    }

    /**
     * Add car to user
     * @param car Car Info
     * @param userId id of User related to this car
     */
    @RequestMapping(method = RequestMethod.POST)
    public void addCar(@RequestBody Car car, @PathVariable Long userId) {
        carService.addCar(car, userId);
    }

}
