package com.kyka.data.service.impl;

import ch.qos.logback.core.util.COWArrayList;
import com.kyka.data.dao.CarDao;
import com.kyka.data.entity.Car;
import com.kyka.data.entity.Garage;
import com.kyka.data.service.GarageService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {

    //车的数量
    private int numberOfCar=50;

    @Autowired
    private CarDao carDao;

    private Garage garage=new Garage();

    @Override
    public void createGarage(){
        List<Car> cars=carDao.getCars();
        for(Car car:cars){
            garage.getCarStack().add(car);
        }
    }

    @Override
    public int getInformation(){
        int num=numberOfCar-garage.getCarStack().size();
        return num;
    }

    @Override
    public boolean getIn(Car car) {
        if(garage.getCarStack().size()<50){
            car.setWaitingId(-1);
            car.setId(garage.getCarStack().size()+1);
            car.setState(0);
            garage.getCarStack().add(car);
            carDao.getIn(car);
            return true;
        }
        return false;
    }

    @Override
    public boolean getOut(String licenseNum) {
        if(garage.getCarStack().size()>0){
            ArrayList<Car> waiting=new ArrayList<>();
            for(Car car:garage.getCarStack()){
                if(car.getLicenseNum().equals(licenseNum)){
                    garage.getCarStack().remove(car);
                }else{
                    car.setId(-1);
                    car.setState(1);
                    carDao.getOut(car.getId());
                    waiting.add(car);
                }
            }
            for(Car car:waiting){
                car.setState(0);
                car.setId(garage.getCarStack().size()+1);
                carDao.getIn(car);
                garage.getCarStack().add(car);
            }
            return true;
        }
        return false;
    }
}
