package com.example.Order;

import com.example.MealClasses.MealA;
import com.example.MealClasses.MealB;
import com.example.MealInterface.Meal;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zq on 2016/9/1.
 */
public class Main {

    public static final Class MEAL_A = MealA.class;
    public static final Class MEAL_B = MealB.class;

    public static void main(String[] arg){
        try {

            order(MEAL_A);
            order(MEAL_B);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void order(Class clazz)
            throws IllegalAccessException,
            InvocationTargetException, InstantiationException {

        Meal meal = MealFactory.createMeal(clazz);
        System.out.println(meal.getName()+" "+meal.getPrice());

    }

}
