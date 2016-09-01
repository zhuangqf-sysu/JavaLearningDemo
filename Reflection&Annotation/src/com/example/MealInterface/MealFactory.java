package com.example.MealInterface;

import com.example.MealInterface.Meal;
import com.example.MealInterface.MealAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by zq on 2016/9/1.
 */
public class MealFactory {

    public static Meal createMeal(Class mealClass)
            throws IllegalAccessException,
            InstantiationException, InvocationTargetException {

        if(!isImplementMealInterface(mealClass))
            throw new IllegalArgumentException(mealClass.getName()
                    + " doesn`t implement interface:"+ Meal.class.getName());

        Constructor constructor = getConstructor(mealClass);

        if(constructor==null)
            throw new IllegalArgumentException(mealClass.getName()
                    + " doesn`t implement Constructor:"
                    + mealClass.getSimpleName()+"(String name,double price)");

        String name = null;
        double price = 0;

        Annotation annotation = mealClass.getAnnotation(MealAnnotation.class);
        if(annotation instanceof MealAnnotation){
            MealAnnotation mealAnnotation = (MealAnnotation)annotation;
            name = mealAnnotation.name();
            price = mealAnnotation.price();
        }

        Meal meal = (Meal) constructor.newInstance(name,price);

        return meal;
    }

    private static Constructor getConstructor(Class mealClass) {
        Constructor[] constructors = mealClass.getConstructors();
        for(Constructor constructor:constructors){
            if(constructor.getParameterCount()!=2) continue;
            Class[] parameterTypes = constructor.getParameterTypes();
            if(!String.class.equals(parameterTypes[0])) continue;
            if(!double.class.equals(parameterTypes[1])) continue;
            return constructor;
        }
        return null;
    }

    private static boolean isImplementMealInterface(Class mealClass) {

        Class[] interfaces = mealClass.getInterfaces();

        for(Class clazz : interfaces)
            if(Meal.class.equals(clazz))
                return true;

        return false;
    }

}
