package com.cs.gson;

import com.cs.util.Tree;
import com.google.gson.Gson;

/**
 * @author chenshuai
 * @date 2020/2/17 12:47
 * HelloWord.class
 */
public class HelloWord {
    public static void main(String[] args) {
        Gson gson = new Gson();


        Tree tree = new Tree("111");

        tree.setLeft(new Tree("222"));
        tree.setRight(new Tree("333"));
        System.out.println(gson.toJson(tree));
    }
}
