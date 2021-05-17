package com.example.youownme.Receiver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReceiversTest {
    Receivers receiver;

    @Before
    public void setUp() throws Exception {
        receiver=new Receivers("张三","2020年05月06日","100");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        assertEquals(receiver.getName(),"张三");
    }

    @Test
    public void getTime() {
        assertEquals(receiver.getTime(),"2020年05月06日");
    }

    @Test
    public void getMoney() {
        assertEquals(receiver.getMoney(),"100￥");
    }

    @Test
    public void setName() {
        receiver.setName("李四");
        assertNotEquals(receiver.getName(),"张三");
        assertEquals(receiver.getName(),"李四");
    }

    @Test
    public void setTime() {
        receiver.setTime("2020-12-15");
        assertNotEquals(receiver.getTime(),"2020年05月06日");
        assertEquals(receiver.getTime(),"2020-12-15");
    }

    @Test
    public void setMoney() {
        receiver.setMoney("200");
        assertEquals(receiver.getMoney(),"200￥");
    }
}