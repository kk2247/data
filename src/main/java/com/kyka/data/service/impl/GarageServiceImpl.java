package com.kyka.data.service.impl;

import com.kyka.data.entity.Car;
import com.kyka.data.entity.Garage;
import com.kyka.data.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GarageServiceImpl implements GarageService {

//    @Autowired
//    private Garage garage;

    @Override
    public boolean getIn(Car car) {

        return false;
    }

    @Override
    public boolean getOut(int licenseNum) {
        return false;
    }
}
