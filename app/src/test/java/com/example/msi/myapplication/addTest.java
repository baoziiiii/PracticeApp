package com.example.msi.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by MSI on 2018/1/29.
 */
public class addTest {

    @Test
    public void testAdd() throws Exception {
        assertEquals(new add().add(2,5),6);
    }
}