package test;

import com.example.buffer.BlockingQueueProduceBuffer;
import com.example.buffer.LockProduceBuffer;
import com.example.role.Consumer;
import com.example.role.Producer;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

/**
 * Created by zq on 2016/8/30.
 */
public class ExecutorTest {
    private static ExecutorService executor = Executors.newFixedThreadPool(20);

    public static void main(String[] arg){
//        test1(BlockingQueueProduceBuffer.class);
//        test2(BlockingQueueProduceBuffer.class);

//        test1(LockProduceBuffer.class);
//        test2(LockProduceBuffer.class);

        executor.shutdown();
    }

    //生产快与消费
    private static void test1(Class buffer){
        executor.submit(new Producer(5,buffer));
        executor.submit(new Producer(5,buffer));
        executor.submit(new Consumer(10,buffer));
    }

    //消费快于生产
    private static void test2(Class buffer){
        executor.submit(new Consumer(5,buffer));
        executor.submit(new Consumer(5,buffer));
        executor.submit(new Producer(10,buffer));
    }
}
