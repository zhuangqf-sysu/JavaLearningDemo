package com.example.role;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by zq on 2016/8/30.
 */
public class Consumer implements Runnable {

    int count = 1;

    Class buffer;

    public Consumer(int count,Class buffer){
        this.count = count;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for(int i=0;i<count;i++) {
            try {
                Method method = buffer.getMethod("consume");
                Integer n = (Integer) method.invoke(null);
                System.out.println(
                        Thread.currentThread().getName() + " consume "+ n );
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
