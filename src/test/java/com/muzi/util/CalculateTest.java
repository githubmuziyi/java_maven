package com.muzi.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by muzi on 2017/11/11.
 */
public class CalculateTest {

    @Test
    public void testAdd() {
        Assert.assertEquals(6, new Calculate().add(3, 3));
    }

    @Test
    public void testSubtract() {
        Assert.assertEquals(3, new Calculate().subtract(5, 2));
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals(4, new Calculate().multiply(2, 2));
    }

    @Test
    public void testDivide() {
        Assert.assertEquals(2, new Calculate().divide(4, 2));
    }
}
