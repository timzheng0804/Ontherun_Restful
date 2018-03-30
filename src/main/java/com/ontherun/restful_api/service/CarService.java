package com.ontherun.restful_api.service;

import com.ontherun.restful_api.domain.Car;

public interface CarService {

    /**
     * Get Car associated with userId
     * @param userId
     * @return Car
     */
    Car getCar(Long userId);

    /**
     * Add the type of car that the user owns
     * @param car
     * @param userId
     */
    void addCar(Car car, Long userId);

}
