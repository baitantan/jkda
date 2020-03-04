package com.cs.util;

import java.util.Objects;

/**
 * @author chenshuai
 * @date 2020/2/17 12:49
 * Tree.class
 */
public class Tree {
    Tree left;
    Tree right;
    String data;
    public Tree(String data){
        this.data = data;
    }

    public Tree() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(left, tree.left) &&
                Objects.equals(right, tree.right) &&
                Objects.equals(data, tree.data);
    }


    @Override
    public int hashCode() {
        return Objects.hash(left, right, data);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "left=" + left +
                ", right=" + right +
                ", data='" + data + '\'' +
                '}';
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    public String getData() {
        return data;
    }
}
