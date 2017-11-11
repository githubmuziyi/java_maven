package com.muzi.util;

import org.junit.*;

/**
 * Created by muzi on 2017/11/11.
 */
public class CalculateTest {

    /**
     * @BeforeClass修饰的方法会在所有方法被调用前执行，而且该方法是静态的，所以测试类被加载后就会运行，而且只在内存中存在一份，适合加载配置文件
     */
    @BeforeClass
    public static void testBeforeClass() {
        System.out.println("BeforeClass");
    }

    /**
     * @AfterClass修饰的方法通常用来对资源的清理，如关闭数据库的链接
     */
    @AfterClass
    public static void testAfterClass(){
        System.out.println("AfterClass");
    }

    /**
     * @Before修饰的方法会在每个测试方法前执行一次
     */
    @Before
    public void testBefore() {
        System.out.println("Before");
    }

    /**
     * @After修饰的方法会在每个测试方法后执行一次
     */
    @After
    public void testAfter() {
        System.out.println("After");
    }

    /**
     * @Ignore所修饰的方法会被测试运行器忽略
     */
    @Ignore
    @Test
    public void testTest() {
        System.out.println("testTest");
    }

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
