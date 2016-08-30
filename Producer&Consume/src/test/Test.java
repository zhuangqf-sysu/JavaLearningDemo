package test;

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
        testBlockingQueueProduceBuffer();
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

    private static void testBlockingQueueProduceBuffer() {
        //生产快于消费
        addThread(PRODUCER,5,BlockingQueueProduceBuffer.class);
        addThread(CONSUMER,10,BlockingQueueProduceBuffer.class);
        addThread(PRODUCER,5,BlockingQueueProduceBuffer.class);

        //消费快于生产
        addThread(PRODUCER,10,BlockingQueueProduceBuffer.class);
        addThread(CONSUMER,5,BlockingQueueProduceBuffer.class);
        addThread(CONSUMER,5,BlockingQueueProduceBuffer.class);
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
