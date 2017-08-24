package com.example.activitydemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.activitydemo.R;

/**
 * Created by Lbin on 2017/8/24.
 */

public class singleInstanceActivity extends TestLaunchActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_launch);
        setTitle("第五个页面");
    }


}
