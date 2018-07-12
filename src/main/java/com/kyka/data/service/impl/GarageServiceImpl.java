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
import java.util.Stack;

@Service
public class GarageServiceImpl implements GarageService {

    //车的数量
    private int numberOfCar=50;

    private CarDao carDao;

    private Garage garage;

    @Autowired
    public GarageServiceImpl(CarDao carDao){
        this.carDao=carDao;
        garage=new Garage();
        List<Car> cars=carDao.getCars();
        for(Car car:cars){
            garage.getCarStack().add(car);
        }
    }

    @Override
    public Garage createGarage(){
        Garage garage1=new Garage();
        List<Car> cars=carDao.getCars();
        for(Car car:cars){
            garage1.getCarStack().add(car);
        }
        return garage1;
    }

    @Override
    public int getInformation(){
        int num=numberOfCar-carDao.getCars().size();
        return num;
    }

    @Override
    public List<Car> getCars() {
        return carDao.getCars();
    }

    @Override
    public boolean getIn(Car car) {
        for(Car add:garage.getCarStack()){
            if(add.getLicenseNum().equals(car.getLicenseNum())){
                return false;
            }
        }
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
        createGarage();
        if(garage.getCarStack().size()>0){
            ArrayList<Car> waiting;
            int size=garage.getCarStack().size();
            Stack<Car> car=garage.getCarStack();
            for(int i=0;i<size;i++){
                if(car.get(i).getLicenseNum().equals(licenseNum)){
                    carDao.getOut(car.get(i).getId());
                    garage.getCarStack().remove(car.get(i));
                    break;
                }else{
                    carDao.waiting(car.get(i).getId());
                    garage.getCarStack().remove(car.get(i));
                    i--;
                }
            }
//            for(Car car:garage.getCarStack()){
//                if(car.getLicenseNum().equals(licenseNum)){
//                    garage.getCarStack().remove(car);
//                    carDao.getOut(car.getId());
//                }else{
//                    garage.getCarStack().remove(car);
//                    carDao.waiting(car.getId());
//                }
//            }
            waiting= (ArrayList<Car>) carDao.getWaitingCars();
            for(Car car1:waiting){
                Car newCar=new Car();
                if((newCar=carDao.getCar(car1.getLicenseNum()))!=null){
                    newCar.setState(0);
                    garage.getCarStack().add(newCar);
                    carDao.enter(newCar.getId());
                }else{
                    if(garage.getCarStack().size()>=50){
                        return true;
                    }
                    car1.setState(0);
                    car1.setId(garage.getCarStack().size()+1);
                    carDao.getIn(car1);
                    garage.getCarStack().add(car1);
                }
            }
            return true;
        }
        return false;
    }
}
