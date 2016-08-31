package com.example.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by zq on 2016/8/31.
 */
public class Client {
    public static void main(String[] arg){
        Socket socket = new Socket();
        try {
            socket.setSoTimeout(100000);
            socket.connect(
                    new InetSocketAddress(InetAddress.getLoopbackAddress(),2016),10000);
            if(!socket.isConnected()) {
                System.out.println("not connect");
                return;
            }
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            Scanner scaner1 = new Scanner(socket.getInputStream());
            Scanner scaner2 = new Scanner(System.in);

            String a = "hello world";
            while(socket.isConnected()){
                if(scaner1.hasNext()){
                    a = scaner1.nextLine();
                    System.out.print(a);
                }
                if(scaner2.hasNext()){
                    a = scaner2.nextLine();
                    output.println(a);
                    output.flush();
                }
                if(a.contains("exit")) break;
            }
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
}
