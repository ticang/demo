package com.example.demo.test;

public class myThread extends Thread {

    // This method will be executed when this thread is executed
    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}