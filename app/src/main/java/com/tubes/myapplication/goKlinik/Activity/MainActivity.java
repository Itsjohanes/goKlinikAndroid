package com.tubes.myapplication.goKlinik.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tubes.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private int waktu_loading=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menghilangkan Title Bar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke home activity
                Intent launch=new Intent(MainActivity.this, LaunchActivity.class);
                startActivity(launch);
                finish();
            }
        },waktu_loading);
    }
}