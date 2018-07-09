package com.kyka.data.dao;

import com.kyka.data.entity.Car;

import java.util.List;

public interface CarDao {
    int getIn(Car car);
    int getOut(int id);
    List<Car> getWaitingCars();
    List<Car> getCars();
}
