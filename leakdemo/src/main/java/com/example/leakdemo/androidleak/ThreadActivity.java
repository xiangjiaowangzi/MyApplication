package com.example.leakdemo.androidleak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.leakdemo.R;

/**
 * Created by Lbin on 2017/10/11.
 */

public class ThreadActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }

    public void setThrad2000(View view){
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(7000); // 这里有点奇怪；这里的时间大小对捕获内存泄露是有影响的
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void setThradAll(View view){
        new Thread(){
            @Override
            public void run() {
                try {
                    while (true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadActivity.class);
        context.startActivity(starter);
    }

}
