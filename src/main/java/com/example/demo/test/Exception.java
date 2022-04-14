package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class Exception extends Throwable {
    public static void main(String[] args) {
        System.out.println(Long.parseLong("20211206121359".substring(0, 14)));
        long activeTime = Long.parseLong("20211206121359".substring(0, 14));
        long inactiveTime = Long.parseLong("20220331121359".substring(0, 14));
        long date = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        if ((date >= activeTime && date <= inactiveTime)) {
            System.out.println("111111");
        } else {
            System.out.println("222222");
        }
        int[] aaa = new int[4];
        try {
            aaa[6] = 1;
        } catch (RuntimeException e) {
            System.out.println("1111");
            e.printStackTrace();
            System.out.println("2222");
        }
        System.out.println("3333");


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
