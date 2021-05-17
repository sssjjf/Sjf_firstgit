package com.example.youownme;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SendersBank {
    private ArrayList<Sender> arrayListSender=new ArrayList<>();
    private Context context;
    private final String SEND_FILE_NAME="senders.txt";
    public SendersBank(Context context)
    {
        this.context=context;
    }
    public ArrayList<Sender> getSender() {
        return arrayListSender;
    }

    public void Save()
    {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(context.openFileOutput(SEND_FILE_NAME,Context.MODE_PRIVATE));
            oos.writeObject(arrayListSender);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Load()
    {
        ObjectInputStream ois = null;
        arrayListSender=new ArrayList<>();
        try {
            ois = new ObjectInputStream(context.openFileInput(SEND_FILE_NAME));
            arrayListSender = (ArrayList<Sender>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
