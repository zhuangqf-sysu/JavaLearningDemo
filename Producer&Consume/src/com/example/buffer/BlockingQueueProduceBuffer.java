package com.example.buffer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * Created by zq on 2016/8/30.
 */
public class BlockingQueueProduceBuffer {

    private final static Integer MAX_COUNT = 100;

    private static BlockingQueue<Integer>produces
            = new ArrayBlockingQueue<Integer>(MAX_COUNT);

    public static void product(Integer n) {
        try {
            produces.put(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Integer consume(){
        try {
            return produces.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
