package com.example.demo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class time {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {

            int a = getMonth(scanner.nextLine());
            System.out.println(a);
        }
        long minDay = getMinDay("20201207");
        System.out.println(minDay);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat maxDateSDF = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse("2021/11/11");
        String format1 = sdf.format(sdf.parse("2021/11/11"));

        String date2 = "2020-01-01 00:00:00";
        System.out.println(date2.replace("-", ""));
        System.out.println(format1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dateTime = new Date();
        Date dBefore = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        dBefore = calendar.getTime();
        String date = simpleDateFormat.format(dBefore);
        //最小日期
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(dBefore);
        calendar1.add(Calendar.YEAR, -3);
        String date1 = simpleDateFormat.format(calendar1.getTime());
        System.out.println(date+ "111"+date1);
        String preDay = getPreDay("20210211");
        System.out.println("11111111"+preDay);
    }

    private static String getPreDay(String strData) {
        String preDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(strData);
            System.out.println(date);
        } catch (java.text.ParseException e) {
            System.out.println(e);
        }

        preDate = sdf.format(date);
        return preDate;
    }

    public static long getMinDay(String netDate) throws ParseException {
        Date d1 = DATE_FORMAT.parse(netDate);
        Date d2 = new Date();
        long diff = (d2.getTime() - d1.getTime())/(1000 * 60 * 60 * 24);
        return  diff;
    }

    public static int getMonth(String start1) throws ParseException {
        Date end = DATE_FORMAT.parse("20211231");
        Date start = DATE_FORMAT.parse(start1);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);
        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        return year * 12 + month + 1;

    }
}
