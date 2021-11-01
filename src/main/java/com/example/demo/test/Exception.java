package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
@Slf4j
public class Exception extends Throwable {
    public static void main(String[] args) {
        int s = 0;
        if (s == 0)
            s = 1;
        s = 2;
        System.out.println(s);
        System.out.println(run());
    }

    public static int run() {
        int i = 0;
        if (i == 0) {
            try {
                i = 1 / 0;
            } catch (java.lang.Exception e) {
//                log.error("111{}", e.getMessage());
//                log.error("111"+e.getMessage());
//                log.error("111"+e);
                log.error("111{}", e);
                log.info("111{}", e);
                log.info("111{}", e.getMessage());



                return 2;
            } finally {
            return i;
            }
        }
        return 1;
    }



}
