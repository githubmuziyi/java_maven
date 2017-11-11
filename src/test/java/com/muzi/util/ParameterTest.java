package com.muzi.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * Created by muzi on 2017/11/12.
 * 对多组数据同时进行测试
 *  1.更改默认的测试器为RunWith(Parameterized.class)
 *  2.声明变量来存放预期值和返回值
 *  3.声明一个返回值为Collection的公共静态方法，并使用@Parameters进行修饰
 *  4.为测试类声明一个带有参数的公共构造函数，并在其中为声明变量赋值
 */

@RunWith(Parameterized.class)
public class ParameterTest {

    int expected = 0;
    int input1 = 0;
    int input2 = 0;

    @Parameterized.Parameters
    public static List<Object[]> t() {
        return Arrays.asList(new Object[][]{
                {3, 1, 2},
                {4, 2, 2}
        });
    }

    public ParameterTest(int expected, int input1, int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(expected, new Calculate().add(input1, input2));
    }
}
