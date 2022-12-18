package com.tubes.myapplication.goKlinik.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tubes.myapplication.R;
import com.tubes.myapplication.databinding.ActivityMainBinding;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnmasuk, btndaftar;
    private static SharedPreferences preferences;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        btndaftar=(Button)findViewById(R.id.btn_daftar);
        btnmasuk=(Button)findViewById(R.id.btn_masuk);

        btndaftar.setOnClickListener(this);
        btnmasuk.setOnClickListener(this);
        preferences = getSharedPreferences("LOGIN_DETAILS", MODE_PRIVATE);

        if(preferences.contains("username") && preferences.contains("password") ){

            Intent intent = new Intent(LaunchActivity.this, Profil.class);
            intent.putExtra("username", preferences.getString("username","" ));
            startActivity(intent);
        }
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_daftar)
        {
            Intent intent_daftar=new Intent(LaunchActivity.this, DaftarActivity.class);
            startActivity(intent_daftar);
        }
        else if (view.getId()==R.id.btn_masuk)
        {
            Intent intent_masuk=new Intent(LaunchActivity.this, MasukActivity.class);
            startActivity(intent_masuk);
        }
    }
    public static void saveStudentPreferences(String username, String password){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }
    public static void clearStudentPref(){
        preferences.edit().remove("username").commit();
        preferences.edit().remove("password").commit();
    }
}
