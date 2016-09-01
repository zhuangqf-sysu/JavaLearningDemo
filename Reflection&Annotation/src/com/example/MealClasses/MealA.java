package com.example.MealClasses;

import com.example.MealInterface.Meal;
import com.example.MealInterface.MealAnnotation;

/**
 * Created by zq on 2016/9/1.
 */

@MealAnnotation(price = 10,name = "meal_a")

public class MealA implements Meal {

    private double price;
    private String name;

    public MealA(String name,double price){
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
