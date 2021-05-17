package com.example.youownme;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//储存人情项目
public class ReceiveBank {
    private ArrayList<ReceiveItem> arrayListReceive =new ArrayList<>();
    private Context context;
    private final String RECEIVE_FILE_NAME="receive.txt";
    public ReceiveBank(Context context)
    {
        this.context=context;
    }
    public ArrayList<ReceiveItem> getArrayListReceive() {
        return arrayListReceive;
    }

    public void setArrayListReceive(ArrayList<ReceiveItem> arrayListReceive){
        this.arrayListReceive=arrayListReceive;}

    public void Save()
    {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(context.openFileOutput(RECEIVE_FILE_NAME,Context.MODE_PRIVATE));
            oos.writeObject(arrayListReceive);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Load()
    {
        ObjectInputStream ois = null;
        arrayListReceive =new ArrayList<>();
        try {
            ois = new ObjectInputStream(context.openFileInput(RECEIVE_FILE_NAME));
            arrayListReceive = (ArrayList<ReceiveItem>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
