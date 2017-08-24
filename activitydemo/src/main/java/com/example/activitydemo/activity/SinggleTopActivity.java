package com.example.activitydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.activitydemo.R;

/**
 * Created by Lbin on 2017/8/24.
 */

public class SinggleTopActivity extends TestLaunchActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_launch);
        setTitle("第三个页面");
    }

}
