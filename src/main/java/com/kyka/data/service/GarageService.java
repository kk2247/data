package com.kyka.data.service;

import com.kyka.data.entity.Car;

public interface GarageService {
    boolean getIn(Car car);
    boolean getOut(int licenseNum);
}
