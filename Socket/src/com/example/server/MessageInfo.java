package com.example.server;

import java.util.*;

/**
 * Created by zq on 2016/8/31.
 */
public class MessageInfo {

    private Calendar timeStamp;
    private String name;
    private String message;

    private static List<MessageInfo> messageInfoList
            = Collections.synchronizedList(new ArrayList<>());

    public MessageInfo(String name,String message){
        this.timeStamp = Calendar.getInstance();
        this.name = name;
        this.message = message;
    }

    public static void addMessageInfo(String name,String message){
        MessageInfo messageInfo = new MessageInfo(name,message);
        messageInfoList.add(messageInfo);
    }

    public static String getMessage(int i){
        if(i<0||i>messageInfoList.size()) return null;

        MessageInfo messageInfo =  messageInfoList.get(i);
        return messageInfo.toString();
    }

    public static int getCount() {
        return messageInfoList.size();
    }

    @Override
    public String toString(){
        int y = timeStamp.get(Calendar.YEAR);
        int m = timeStamp.get(Calendar.MONTH);
        int d = timeStamp.get(Calendar.DATE);
        int h = timeStamp.get(Calendar.HOUR_OF_DAY);
        int mi= timeStamp.get(Calendar.MINUTE);
        int s = timeStamp.get(Calendar.SECOND);
        return name + " [" + y+"/"+m+"/"+d + " " + h+":"+mi+":"+s + "]\t"
                + message;
    }

}
