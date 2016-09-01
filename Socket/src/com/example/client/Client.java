package com.example.client;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by zq on 2016/8/31.
 */
public class Client {

    private static Socket socket;
    private static boolean isShutdown = false;

    public static void main(String[] arg) throws InterruptedException {
        socket = new Socket();
        try {
            socket.setSoTimeout(100000);
            socket.connect(
                    new InetSocketAddress(InetAddress.getLoopbackAddress(),2016),10000);
            if(!socket.isConnected()) {
                System.out.println("not connect");
                return;
            }

            Sender sender1 = new Sender(socket.getInputStream(),System.out);
            Sender sender2 = new Sender(System.in,socket.getOutputStream());

            Thread thread1 = new Thread(sender1);
            Thread thread2 = new Thread(sender2);

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Sender implements Runnable{

        private Scanner input;
        private PrintWriter output;

        public Sender(InputStream input,OutputStream output){
            this.input = new Scanner(input);
            this.output = new PrintWriter(output);
        }

        @Override
        public void run() {
            while(socket.isConnected()&&!isShutdown){
                if(input.hasNext()){
                    String message = input.nextLine();
                    output.println(message);
                    output.flush();
                    if(message.indexOf("quit")!=-1){
                        isShutdown = true;
                        break;
                    }
                }
            }
        }
    }

}
