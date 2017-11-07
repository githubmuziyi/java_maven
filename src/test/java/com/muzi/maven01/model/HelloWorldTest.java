package com.muzi.maven01.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by muzi on 2017/11/7.
 * 测试类
 */
public class HelloWorldTest {

    @Test
    public void testHello() {
        Assert.assertEquals("Hello World!", new HelloWorld().sayHello());
    }
}
