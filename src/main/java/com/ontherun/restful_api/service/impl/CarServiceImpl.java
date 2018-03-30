package com.ontherun.restful_api.service.impl;

import com.ontherun.restful_api.domain.Car;
import com.ontherun.restful_api.domain.CarRepository;
import com.ontherun.restful_api.domain.UserInfo;
import com.ontherun.restful_api.domain.UserRepository;
import com.ontherun.restful_api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private UserRepository userRepository;
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Car getCar(Long userId) {
        return carRepository.findByUserId(userId);
    }

    @Override
    public void addCar(Car car, Long userId) {
        UserInfo user = userRepository.findById(userId);
        carRepository.save(new Car(user, car.getType(), car.getColor()));
    }

}
