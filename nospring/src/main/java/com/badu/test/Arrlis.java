package com.badu.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arrlis {

    public static void main(String[] args) {

        String s ="1,2,3";
        List<String> strings = Arrays.asList(s.split(","));
        List<String> strings1 = new ArrayList<>(strings);

        System.out.println(strings1);
        strings1.remove(1);
        System.out.println(strings1);
    }
}
