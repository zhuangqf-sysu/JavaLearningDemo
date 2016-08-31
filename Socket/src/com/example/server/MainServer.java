package com.example.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zq on 2016/8/31.
 */
public class MainServer {

    private final static int MAX_THREAD_COUNT = 100;

    private static ExecutorService executor = Executors.newFixedThreadPool(MAX_THREAD_COUNT);

    public static void main(String[] arg){

        try {
            ServerSocket serverSocket = new ServerSocket(2016);
            while(true){
                Socket socket = serverSocket.accept();
                InfoCheckThread thread = new InfoCheckThread(socket);
                executor.submit(thread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }

    }
}
