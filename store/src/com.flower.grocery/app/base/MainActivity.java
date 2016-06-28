package com.flower.grocery.app.base;

import android.app.Activity;
import android.os.Bundle;

import com.flower.grocery.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

}
