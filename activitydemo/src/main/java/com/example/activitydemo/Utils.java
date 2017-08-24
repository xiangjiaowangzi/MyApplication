package com.example.activitydemo;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Lbin on 2017/8/24.
 */

public class Utils {

    public static void log(String tag , String s){
        if (TextUtils.isEmpty(tag)){
            tag = "LB" ;
        }
        Log.e(tag , s);
    }
}
