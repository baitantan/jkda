package com.cs;


import com.google.common.base.Splitter;

/**
 * @author chenshuai
 * @date 2020/2/16 17:25
 * GuaVa.class
 */
public class GuaVa {
    public static void main(String[] args) {

        int i = 1, j = 2;

        Splitter splitter = Splitter.on(",");
        System.out.println(splitter.split("a,b,c"));

    }
}
