package com.example.client;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by zq on 2016/8/31.
 */
public class Client {

    private static Socket socket;

    public static void main(String[] arg){
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

            new Thread(sender1).start();
            new Thread(sender2).start();

            while(socket.isConnected());

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
            while(socket.isConnected()){
                if(input.hasNext()){
                    output.println(input.nextLine());
                    output.flush();
                }
            }
        }
    }

}
