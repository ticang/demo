package com.example.demo.test;

/**
 * @author wk
 * @date 2022/4/7
 * @apiNote
 */
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//1.创建一个线程类，实现Callable接口，
//2.Callable会报一个黄色的警告，原因：可以加个泛型，这个泛型，是返回值对应的类型。
//3.我们这个题是产生随机数，所以加个泛型Integer
public class RanDomCallable implements Callable<Integer> {

    //4.一旦上面的泛型确定了，那么这个重写的方法的返回值类型就是Integer了。
    @Override
    public Integer call() throws InterruptedException {
        Thread.sleep(2000);
        return new Random().nextInt(10);
    }

}
