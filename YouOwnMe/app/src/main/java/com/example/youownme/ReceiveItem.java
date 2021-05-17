package com.example.youownme;

import com.example.youownme.Receiver.Receivers;

import java.io.Serializable;
import java.util.ArrayList;

public class ReceiveItem implements Serializable {
    private  String ReceiveItemName;
    private  String ReceiveItemTime;
    private  ArrayList<Receivers> child_list =new ArrayList<Receivers>();

    public ReceiveItem(){}
    public ReceiveItem(String ReceiveItemName,String ReceiveItemTime, ArrayList<Receivers> child_list){
        this.ReceiveItemTime = ReceiveItemTime;
        this.ReceiveItemName = ReceiveItemName;
        this.child_list = child_list;
    }

    public int getChildSize() {
        return child_list.size();
    }

    public String getReceiveItemName() {
        return ReceiveItemName;
    }

    public void setReceiveItemName(String receiveItemName) {
        this.ReceiveItemName = receiveItemName;
    }

    public ArrayList<Receivers> getChild_list() {
        return child_list;
    }

    public void setChild_list(ArrayList<Receivers> child_list) {
        this.child_list = child_list;
    }

    public String getReceiveItemTime() {
        return ReceiveItemTime;
    }

    public void setReceiveItemTime(String receiveItemTime) {
        ReceiveItemTime = receiveItemTime;
    }
}
