package com.muzi.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by muzi on 2017/11/12.
 * 测试套件
 * 组织测试类一起运行的测试类
 *  1.一个测试套件类里面不包含其他方法
 *  2.更改测试运行器Suite.class
 *  3.将要测试的类作为数组传入到Suite.SuiteClasses({})
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({TaskTest01.class, TaskTest02.class})
public class SuiteTest {

}
