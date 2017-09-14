package order;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.drag.DragRecyclerViewAct;

import org.loader.layoutmanager.MainActivity;
import org.loader.layoutmanager.R;

/**
 * Created by Lbin on 2017/9/14.
 */

public class OrderAcitvity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn1 :
                startActivity(MainActivity.class);
                break;
            case R.id.btn2 :
                startActivity(DragRecyclerViewAct.class);
                break;
        }
    }

    void startActivity(Class<?> clazz){
        startActivity(new Intent(this , clazz));
    }
}
