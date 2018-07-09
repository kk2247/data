package com.kyka.data.dao;

import com.kyka.data.entity.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarDaoTest {

    @Autowired
    private CarDao carDao;

    @Test
    public void getIn() {
        Car car=new Car();
        car.setState(1);
        car.setId(1);
        car.setWaitingId(0);
        car.setLicenseNum("1234");
        carDao.getIn(car);
    }

    @Test
    public void getOut() {
        System.out.println(carDao.getOut(1));
    }

    @Test
    public void getWaitingCars() {
        System.out.println(carDao.getWaitingCars().size());
    }
}