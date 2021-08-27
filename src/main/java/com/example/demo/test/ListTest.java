package com.example.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("111");
        list1.add("121");
        list1.add("131");
        String s = "111,222,333,";
        String[] split = s.split(",");

        System.out.println(Arrays.asList(split));
        System.out.println(String.join("|", Arrays.asList(split)));


    }
}
