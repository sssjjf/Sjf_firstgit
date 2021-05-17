package com.example.youownme;

import com.example.youownme.Receiver.Receivers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SenderTest {
    Sender sender;

    @Before
    public void setUp() throws Exception {
        sender = new Sender("张三","2020年05月06日","100");
    }

    @Test
    public void getName() {
        assertEquals(sender.getName(),"张三");
    }

    @Test
    public void getTime() {
        assertEquals(sender.getTime(),"2020年05月06日");
    }

    @Test
    public void getMoney() {
        assertEquals(sender.getMoney(),"100￥");
    }

    @Test
    public void setName() {
        sender.setName("李四");
        assertNotEquals(sender.getName(),"张三");
        assertEquals(sender.getName(),"李四");
    }

    @Test
    public void setTime() {
        sender.setTime("2020-12-15");
        assertNotEquals(sender.getTime(),"2020年05月06日");
        assertEquals(sender.getTime(),"2020-12-15");
    }

    @Test
    public void setMoney() {
        sender.setMoney("200");
        assertEquals(sender.getMoney(),"200￥");
    }
}