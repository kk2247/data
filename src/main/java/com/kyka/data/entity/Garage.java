package com.kyka.data.entity;

import java.util.Stack;

public class Garage {

    //停车位
    private Stack<Car> carStack;

    public Garage() {
        carStack=new Stack<>();
    }

    public Stack<Car> getCarStack() {
        return carStack;
    }

    public void setCarStack(Stack<Car> carStack) {
        this.carStack = carStack;
    }
}
