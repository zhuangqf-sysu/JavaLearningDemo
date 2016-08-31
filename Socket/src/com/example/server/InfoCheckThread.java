package com.example.server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by zq on 2016/8/31.
 */
public class InfoCheckThread implements Runnable{

    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private String name;
    private String password;

    public InfoCheckThread(Socket socket) {
        this.socket = socket;
        try {
            this.input = new Scanner(socket.getInputStream());
            this.output = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        output.println("Input your name:");
        output.flush();
        name = input.nextLine();

        ClientInfo clientInfo = ClientInfo.queueByName(name);
        if(clientInfo==null) password = addNewClient(name);
        else password = checkPassword(clientInfo);

        if(password==null){
            output.println("error password!");
            output.flush();
        }else {
            output.print("your name:" + name + " ");
            output.println("your password:" + password + "\n");
            output.flush();
        }
        closeSocket();
    }

    private void closeSocket() {
        try {
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(socket.isClosed()){
            System.out.println(name + " exit!");
        }
    }


    private String checkPassword(ClientInfo clientInfo) {
        output.println("Input your password:");
        output.flush();
        String  password = input.nextLine();

        if(clientInfo.isPassword(password)) return password;
        return null;
    }

    private String addNewClient(String name) {
        output.println("Input your password:");
        output.flush();
        String password1 = input.nextLine();
        output.println("Input your password again:");
        output.flush();
        String password2 = input.nextLine();
        if(!password1.equals(password2)) return null;
        new ClientInfo(name,password1);
        return password1;
    }
}
