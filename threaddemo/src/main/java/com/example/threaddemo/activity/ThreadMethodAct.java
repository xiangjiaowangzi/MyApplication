package com.example.threaddemo.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.threaddemo.Utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lbin on 2017/8/25.
 */

public class ThreadMethodAct extends AppCompatActivity {

    private static final String TAG = "ThreadMethodAct";

    /**
     * 线程池参数
     * */
    public static final int COREPOOLSIZE = 3 ; // 线程池中核心线程的数量
    public static final int MAXPOOLSIZE = 3 ; // 线程池中最大线程数量
    // 非核心线程的超时时长，当系统中非核心线程闲置时间超过keepAliveTime之后，则会被回收。
    // 如果ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，则该参数也表示核心线程的超时时长
    public static final long KEPPLIVETIME =  10 * 1000L ; // 10S

    // unit 第三个参数的单位，有纳秒、微秒、毫秒、秒、分、时、天等
    // workQueue 线程池中的任务队列，该队列主要用来存储已经被提交但是尚未执行的任务。存储在这里的任务是由ThreadPoolExecutor的execute方法提交来的。
    // threadFactory  为线程池提供创建新线程的功能，这个我们一般使用默认即可
    // handler 拒绝策略，当线程无法执行新任务时（一般是由于线程池中的线程数量已经达到最大数或者线程池关闭导致的），
    // 默认情况下，当线程池无法处理新线程时，会抛出一个RejectedExecutionException。

    //


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(COREPOOLSIZE , MAXPOOLSIZE , KEPPLIVETIME , TimeUnit.MICROSECONDS
                , new LinkedBlockingQueue<Runnable>() );
        executor.submit(new Runnable() {
            @Override
            public void run() {
                Utils.log("" , " executing ... ");
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        Executor executor1 = Executors.newSingleThreadExecutor();
        Executor executor2 = Executors.newFixedThreadPool(2);
        Executor executor3 = Executors.newCachedThreadPool();
        Executor executor4 = Executors.newScheduledThreadPool(3);
    }


    class MyThread extends Thread{
        @Override
        public void run() {
            log("" + Thread.currentThread());
        }
    }

    void log(String s){
        Utils.log(TAG , s);
    }

}
