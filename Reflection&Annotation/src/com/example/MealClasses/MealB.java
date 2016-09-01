package com.example.MealClasses;

import com.example.MealInterface.Meal;

/**
 * Created by zq on 2016/9/1.
 */
public class MealB implements Meal{

    private String name;
    private double price;

    public MealB(String name,double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
