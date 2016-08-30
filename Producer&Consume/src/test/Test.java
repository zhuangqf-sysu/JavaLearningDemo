package test;

import com.example.buffer.LockProduceBuffer;
import com.example.role.Consumer;
import com.example.buffer.BlockingQueueProduceBuffer;
import com.example.role.Producer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zq on 2016/8/30.
 */
public class Test {

    private final static Boolean PRODUCER = true;
    private final static Boolean CONSUMER = false;

    private static List<Thread> threads;

    public static void main(String[] arg){

        threads = new ArrayList<Thread>();

//        test1(BlockingQueueProduceBuffer.class);
//        test2(BlockingQueueProduceBuffer.class);

//        test1(LockProduceBuffer.class);
//        test2(LockProduceBuffer.class);

        join();
    }

    private static void join() {
        try {
            for (Thread thread : threads)
                thread.join();
        } catch (InterruptedException e){
                e.printStackTrace();
        }
    }

    private static void test1(Class buffer) {
        //生产快于消费
        addThread(PRODUCER, 5, buffer);
        addThread(CONSUMER, 10, buffer);
        addThread(PRODUCER, 5, buffer);
    }
    private static void test2(Class buffer){
        //消费快于生产
        addThread(PRODUCER,10,buffer);
        addThread(CONSUMER,5,buffer);
        addThread(CONSUMER,5,buffer);
    }

    private static void addThread(Boolean flag,int n,Class buffer){
        Thread thread;
        if(PRODUCER.equals(flag))
            thread = new Thread(new Producer(n,buffer));
        else thread = new Thread(new Consumer(n,buffer));
        thread.start();
        threads.add(thread);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
