package com.example.buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zq on 2016/8/30.
 */
public class LockProduceBuffer {

    private static final Integer MAX_COUNT = 100;

    private static List<Integer> produces
            = new ArrayList<Integer>(MAX_COUNT);

    private static final Lock lock = new ReentrantLock();
    private static Condition notFull = lock.newCondition();
    private static Condition notEmpty = lock.newCondition();

    public static void product(Integer n){
        lock.lock();
        try{
            while(produces.size()>=MAX_COUNT){
                notFull.await();
            }
            produces.add(n);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static Integer consume(){
        lock.lock();
        try{
            while (produces.size()<=0){
                notEmpty.await();
            }
            return produces.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        notFull.signalAll();
        return -1;
    }
}
