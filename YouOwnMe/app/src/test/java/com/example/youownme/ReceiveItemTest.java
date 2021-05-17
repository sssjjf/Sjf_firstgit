package com.example.youownme;

import com.example.youownme.Receiver.Receivers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReceiveItemTest {
    ReceiveItem receiveItem ;
    Receivers receivers;
    @Before
    public void setUp() throws Exception {
        receiveItem =new ReceiveItem("乔迁新居","2012年12月5日",new ArrayList<Receivers>());
        receivers=new Receivers("张三","2020年05月06日","100");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getChildSize() {
        assertTrue(receiveItem.getChildSize()==0);
    }

    @Test
    public void getReceiveItemName() {
        assertEquals(receiveItem.getReceiveItemName(),"乔迁新居");
    }
    @Test
    public void getReceiveItemTime() {
        assertEquals(receiveItem.getReceiveItemTime(),"2012年12月5日");
    }

    @Test
    public void getChild_list() {
        assertNotNull(receiveItem.getChild_list());
    }

    @Test
    public void setReceiveItemName() {
        receiveItem.setReceiveItemName("新婚大喜");
        assertEquals(receiveItem.getReceiveItemName(),"新婚大喜");
    }

    @Test
    public void setChild_list() {
        ArrayList<Receivers> list =new ArrayList<Receivers>();
        list.add(receivers);
        receiveItem.setChild_list(list);
        assertTrue(receiveItem.getChildSize()==1);
    }

    @Test
    public void setReceiveItemTime() {
        receiveItem.setReceiveItemTime("2020年12月15日");
        assertEquals(receiveItem.getReceiveItemTime(),"2020年12月15日");
    }
}