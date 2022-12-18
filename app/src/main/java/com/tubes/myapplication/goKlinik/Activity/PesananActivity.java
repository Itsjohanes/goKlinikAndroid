package com.tubes.myapplication.goKlinik.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.tubes.myapplication.R;

public class PesananActivity extends AppCompatActivity {
   ImageView btnSelesai,btnBelum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
        String id_pasien = getIntent().getExtras().getString("id_pasien");
        String username = getIntent().getExtras().getString("username");
        String password = getIntent().getExtras().getString("password");
        String nama = getIntent().getExtras().getString("nama");
        String tgl_lahir = getIntent().getExtras().getString("tgl_lahir");
        String jenkel = getIntent().getExtras().getString("jenkel");
        String no_hp = getIntent().getExtras().getString("no_hp");
        String alamat = getIntent().getExtras().getString("alamat");
        btnBelum = findViewById(R.id.btn_belum);
        btnSelesai = findViewById(R.id.btn_selesai);
        btnBelum.setOnClickListener(v -> {
            Intent intent = new Intent(PesananActivity.this, PesananBelum.class);
            intent.putExtra("id_pasien", id_pasien);
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            intent.putExtra("nama", nama);
            intent.putExtra("tgl_lahir", tgl_lahir);
            intent.putExtra("jenkel", jenkel);
            intent.putExtra("no_hp", no_hp);
            intent.putExtra("alamat", alamat);
            startActivity(intent);
        });
        btnSelesai.setOnClickListener(v -> {
            Intent intent = new Intent(PesananActivity.this, PesananSelesai.class);
            intent.putExtra("id_pasien", id_pasien);
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            intent.putExtra("nama", nama);
            intent.putExtra("tgl_lahir", tgl_lahir);
            intent.putExtra("jenkel", jenkel);
            intent.putExtra("no_hp", no_hp);
            intent.putExtra("alamat", alamat);
            startActivity(intent);
        });
        ActionBar actionBar=getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pesanan Obat");
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed(){
        Intent back = new Intent(PesananActivity.this, Profil.class);
        startActivity(back);
    }
}