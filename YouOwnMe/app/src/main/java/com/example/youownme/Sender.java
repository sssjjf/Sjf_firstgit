package com.example.youownme;

import java.io.Serializable;

public class Sender implements Serializable {
    private String name;
    private String time;
    private String money;

    public Sender(){}
    public Sender(String name,String time,String money){
        this.name=name;
        this.time=time;
        if(money.charAt(money.length()-1)=='￥'){
            this.money = money;
        }else {
            this.money = money + "￥";
        }
    }

    public String getName() {
        return name;
    }
    public String getTime(){
        return time;
    }
    public String getMoney(){
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setMoney(String money) {
        if(money.charAt(money.length()-1)=='￥'){
            this.money = money;
        }else {
            this.money = money + "￥";
        }
    }
}
