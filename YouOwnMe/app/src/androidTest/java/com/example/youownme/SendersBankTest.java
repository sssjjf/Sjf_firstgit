package com.example.youownme;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SendersBankTest {
    SendersBank sendersBank;
    Context context;
    ArrayList<Sender> arrayListSender=new ArrayList<>();
    int len;
    @Before
    public void setUp() throws Exception {
        context =  ApplicationProvider.getApplicationContext();
        sendersBank=new SendersBank(context);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void load() {

    }

    @Test
    public void getSender() {
    }

    @Test
    public void save() {
    }
}