package com.muzi.maven02.util;

import com.muzi.maven01.model.HelloWorld;

/**
 * Created by muzi on 2017/11/7.
 */
public class Speak {

    public String sayHi() {
        return new HelloWorld().sayHello();
    }
}
