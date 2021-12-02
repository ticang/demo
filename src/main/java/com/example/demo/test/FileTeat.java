package com.example.demo.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTeat {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("111||111");
        list.add("111||111");
        list.add("111||111");

        write("d:\\1.txt", list);
    }

    public static void write(String path, List<String> list) {
        BufferedWriter bw = null;
        FileWriter fr = null;
        try {
            //将写入转化为流的形式
            File file = new File(path);
            fr = new FileWriter(file);
            bw = new BufferedWriter(fr);
            //一次写一行
            for (String s : list) {
                bw.write(s);
                bw.newLine();  //换行用
            }
            bw.flush();

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                System.out.println(tempStr);
            }
            FileInputStream fis = new FileInputStream(file);
            System.out.println("111");
            int tempbyte;
            while ((tempbyte = fis.read()) != -1) {
                System.out.print("tempbyte");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
