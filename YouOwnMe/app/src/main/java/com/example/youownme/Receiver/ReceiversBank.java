package com.example.youownme.Receiver;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReceiversBank {
    private ArrayList<Receivers> arrayListReceivers =new ArrayList<>();
    private Context context;
    private String FileName;
    public ReceiversBank(Context context,String filename)
    {
        this.context=context;
        this.FileName = filename;
    }
    public ArrayList<Receivers> getArrayListReceivers() {
        return arrayListReceivers;
    }

    public void Save()
    {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(context.openFileOutput(FileName,Context.MODE_PRIVATE));
            oos.writeObject(arrayListReceivers);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Load()
    {
        ObjectInputStream ois = null;
        arrayListReceivers =new ArrayList<>();
        try {
            ois = new ObjectInputStream(context.openFileInput(FileName));
            arrayListReceivers = (ArrayList<Receivers>) ois.readObject();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
