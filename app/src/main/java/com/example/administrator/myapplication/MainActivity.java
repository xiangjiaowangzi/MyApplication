package com.example.administrator.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
    }

    void findview(){
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1 :
                startActivity(new Intent(this , TestMesureViewAct.class));
                break;
            case R.id.btn2 :
                startActivity(new Intent(this , TestTextAct.class));
                break;
            case R.id.btn3 :
                startActivity(new Intent(this , TestSwipeAct.class));
                break;
            case R.id.btn4 :
                startActivity(new Intent(this , TestTouchAct.class));
                break;
            case R.id.btn5 :
                startActivity(new Intent(this , TestNestedScrollAct.class));
            case R.id.btn6 :
                startActivity(new Intent(this , TestScrollViewAct.class));
        }
    }
}
