package com.kyka.data.dao;

import com.kyka.data.entity.Car;

import java.util.List;

public interface CarDao {
    int getIn(Car car);
    int getOut(int id);
    List<Car> getWaitingCars();
    List<Car> getCars();
    int waiting(int id);
    int enter(int id);
    Car getCar(String licenseNum);
}
