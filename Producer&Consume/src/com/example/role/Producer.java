package com.example.role;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by zq on 2016/8/30.
 */
public class Producer implements Runnable {

    int count = 1;
    Class buffer;

    public Producer(int count,Class buffer){
        this.count = count;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0;i<count;i++) {
            try{
                Integer n = new Random().nextInt();

                Method method = buffer.getMethod("product",Integer.TYPE);
                method.invoke(null,n);

                System.out.println(
                        Thread.currentThread().getName() + " product "+ n );

                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
