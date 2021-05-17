package com.example.youownme;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.example.youownme.Receiver.Receivers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReceiveBankTest {
    ReceiveBank receiveBank;
    ReceiveItem receiveItem ;
    Receivers receivers;
    ArrayList<ReceiveItem> group_list;
    ArrayList<Receivers> child_list;
    Context context;
    int len;
    @Before
    public void setUp() throws Exception {
        context =  ApplicationProvider.getApplicationContext();
        receiveBank=new ReceiveBank(context);
    }

    @Test
    public void load() {
        receiveBank.Load();
    }

    @Test
    public void save() {
        receiveBank.Save();
    }

    @Test
    public void getArrayListReceive() {
        receiveBank.Load();
        group_list = receiveBank.getArrayListReceive();
        assertNotNull(group_list);
    }

    @Test
    public void setArrayListReceive() {
        receiveBank.Load();
        group_list = receiveBank.getArrayListReceive();
        receivers=new Receivers("张三","2020年05月06日","100");
        len =group_list.size();
        child_list =new ArrayList<>();
        child_list.add(receivers);
        receiveItem =new ReceiveItem("乔迁新居","2012年12月5日",child_list);
        group_list.add(receiveItem);
        receiveBank.setArrayListReceive(group_list);
        receiveBank.Save();
        receiveBank.Load();
        group_list = receiveBank.getArrayListReceive();
        assertTrue((len+1) ==group_list.size());
    }

}