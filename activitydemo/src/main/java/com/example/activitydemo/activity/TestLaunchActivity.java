package com.example.activitydemo.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.activitydemo.R;
import com.example.activitydemo.Utils;

/**
 * Created by Lbin on 2017/8/24.
 */

public class TestLaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_launch);
        Utils.log("" , " *****onCreate()方法****** ");
        Utils.log("" , "onCreate：" + getClass().getSimpleName() + " TaskId: " + getTaskId() + " hasCode:" + this.hashCode());
        dumpTaskAffinity();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Utils.log("" , " *****onNewIntent()方法****** ");
        Utils.log("" , "onNewIntent：" + getClass().getSimpleName() + " TaskId: " + getTaskId() + " hasCode:" + this.hashCode());
        dumpTaskAffinity();
    }

    protected void dumpTaskAffinity() {
        try {
            ActivityInfo info = this.getPackageManager()
                    .getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Utils.log("" , "taskAffinity:" + info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *   Intent Flags 属性
     *   Intent.FLAG_ACTIVITY_NEW_TASK  和 standar差不多但是有区别
     *   根据Activity Affinity判断是否需要创建新的Task，然后再创建新的Activit实例放进去。
     *   A应用启动B应用该页面 该页面会在B的Task  不会再A的task
     *
     *   .FLAG_ACTIVITY_CLEAR_TOP： 如果在栈中发现存在Activity实例，则清空这个实例之上的Activity，使其处于栈顶
     *
     *   FLAG_ACTIVITY_SINGLE_TOP：singgletop
     *
     *   FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET：
     *   从后台进入的时候 则它转向的那个Activity以及在那个Activity其上的所有Activity都会在task重置时被清除出task
     *   前提：FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
     *
     *   FLAG_ACTIVITY_RESET_TASK_IF_NEEDED 与上面对应
     *
     *
     * */

    /**
     *   <activity>中定义了几个常见的task相关属性
     *
     *   1. android:allowTaskReparenting
     *   这个属性用来标记一个Activity实例在当前应用退居后台后，是否能从启动它的那个task移动到有共同affinity的task，
     *   “true”表示可以移动，“false”表示它必须呆在当前应用的task中，默认值为false
     *   例子： A 启动 B （2）页面  后台进入B  发现是 B的 （2）页面
     *
     *   2.android:alwaysRetainTaskState
     *   这个属性用来标记应用的task是否保持原来的状态，“true”表示总是保持，“false”表示不能够保证，
     *   默认为“false”。此属性只对task的根Activity起作用，其他的Activity都会被忽略。
     *
     *   3.android:clearTaskOnLaunch
     *   这个属性用来标记是否从task清除除根Activity之外的所有的Activity，“true”表示清除，“false”表示不清除，
     *   默认为“false”。同样，这个属性也只对根Activity起作用，其他的Activity都会被忽略。
     *
     *   4.android:finishOnTaskLaunch
     *   这个属性和android:allowTaskReparenting属性相似，不同之处在于allowTaskReparenting属性是重新宿主到有共同affinity的task中，
     *   而finishOnTaskLaunch属性是销毁实例。如果这个属性和android:allowReparenting都设定为“true”，则这个属性胜出。
     *
     *   5.android:noHistory
     *   具有此属性标识的Activity当导航到其他Activity上时，Activity栈将不记录其自身，简单来说，当A -> B, 此时Activity栈中只含有B。
          经测试，其onDeatroy函数回调时机是在B中按Back键时触发
     * */

    public void click(View view){
        Intent intent = new Intent(this , TestLaunchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void click1(View view){
        startActivity(new Intent(this , StandardActivity.class));
    }

    public void click2(View view){
        startActivity(new Intent(this , SinggleTopActivity.class));
    }

    public void click3(View view){
        startActivity(new Intent(this , SingleTaskActivity.class));
    }

    public void click4(View view){
        startActivity(new Intent(this , singleInstanceActivity.class));
    }
}
