package com.kyka.data.service;

import com.kyka.data.entity.Car;
import com.kyka.data.entity.Garage;

public interface GarageService {
    boolean getIn(Car car);
    boolean getOut(String licenseNum);
    Garage createGarage();
    int getInformation();
}
