package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Lbin on 2017/7/7.
 */

public class TestSwipeAct extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        LinearLayout view1 = (LinearLayout) findViewById(R.id.contentView);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        view1.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contentView:
                Toast.makeText(TestSwipeAct.this , "点击了item" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn1 :
                Toast.makeText(TestSwipeAct.this , "点击了第一个按钮" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2 :
                Toast.makeText(TestSwipeAct.this , "点击了第二个按钮" , Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
