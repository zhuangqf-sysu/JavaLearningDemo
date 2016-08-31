package com.example.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zq on 2016/8/31.
 */
public class ClientInfo {

    private static Map<String,ClientInfo>clientInfoMap
            = new ConcurrentHashMap<String,ClientInfo>();

    private String name;
    private String password;

    public ClientInfo(String name,String password){
        this.name = name;
        this.password = password;
        clientInfoMap.put(name,this);
    }

    public boolean isPassword(String password){
        if(password == null) return false;
        else if(password.equals(this.password)) return true;
        else return false;
    }

    public static ClientInfo queueByName(String name){
        if(clientInfoMap.containsKey(name))
            return clientInfoMap.get(name);
        else return null;
    }

}
