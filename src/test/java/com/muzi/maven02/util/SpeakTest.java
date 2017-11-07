package com.muzi.maven02.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by muzi on 2017/11/7.
 */
public class SpeakTest {

    @Test
    public void SpeakTest() {
        Assert.assertEquals("Hello World!", new Speak().sayHi());
    }
}
