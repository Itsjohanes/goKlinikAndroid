package com.tubes.myapplication.goKlinik.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

public class Profil extends AppCompatActivity {

    private TextView tvUserName, tvNama, tvJenkel, tvTglLahir, tvNoHP, tvAlamat, tvProfil;
    private Button btnEdit,btnKlinik;
    private ImageView btnLogout, btnPesanan, btnKonsultasi;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //calling action bar
        ActionBar actionBar=getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profil");

        String id_pasien = getIntent().getExtras().getString("id_pasien");
        String username = getIntent().getExtras().getString("username");
        String password = getIntent().getExtras().getString("password");
        String nama = getIntent().getExtras().getString("nama");
        String tgl_lahir = getIntent().getExtras().getString("tgl_lahir");
        String jenkel = getIntent().getExtras().getString("jenkel");
        String no_hp = getIntent().getExtras().getString("no_hp");
        String alamat = getIntent().getExtras().getString("alamat");

        tvProfil = findViewById(R.id.tv_profil);
        tvUserName = findViewById(R.id.tv_username);
        tvNama = findViewById(R.id.tv_nama);
        tvTglLahir = findViewById(R.id.tv_tgl_lahir);
        tvJenkel = findViewById(R.id.tv_jenkel);
        tvNoHP = findViewById(R.id.tv_no_hp);
        tvAlamat = findViewById(R.id.tv_alamat);
        btnEdit = findViewById(R.id.btn_edit_profil);
        btnLogout = findViewById(R.id.btn_logout);
        btnPesanan  = findViewById(R.id.btn_pesanan);
        btnKonsultasi = findViewById(R.id.btn_konsultasi);
        btnKlinik = findViewById(R.id.btn_klinik);
        tvProfil.setText("Profil");
        tvUserName.setText("Username : " + username);
        tvNama.setText("Nama : " + nama);
        tvTglLahir.setText("Tanggal Lahir : "+tgl_lahir);
        tvJenkel.setText("Jenis Kelamin : "+jenkel);
        tvNoHP.setText("No Telepon : "+no_hp);
        tvAlamat.setText("Alamat : "+alamat);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, EditProfil.class);
                intent.putExtra("id_pasien", id_pasien);
                intent.putExtra("username", username);
                intent.putExtra("password",password);
                intent.putExtra("nama", nama);
                intent.putExtra("tgl_lahir", tgl_lahir);
                intent.putExtra("jenkel", jenkel);
                intent.putExtra("no_hp", no_hp);
                intent.putExtra("alamat", alamat);
                startActivity(intent);
            }
        });
        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profil.this, PesananActivity.class);
                intent.putExtra("id_pasien", id_pasien);
                intent.putExtra("username", username);
                intent.putExtra("password",password);
                intent.putExtra("nama", nama);
                intent.putExtra("tgl_lahir", tgl_lahir);
                intent.putExtra("jenkel", jenkel);
                intent.putExtra("no_hp", no_hp);
                intent.putExtra("alamat", alamat);
                startActivity(intent);
            }
        });
        btnKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profil.this, KonsultasiActivity.class);
                intent.putExtra("id_pasien", id_pasien);
                intent.putExtra("username", username);
                intent.putExtra("password",password);
                intent.putExtra("nama", nama);
                intent.putExtra("tgl_lahir", tgl_lahir);
                intent.putExtra("jenkel", jenkel);
                intent.putExtra("no_hp", no_hp);
                intent.putExtra("alamat", alamat);
                startActivity(intent);
            }
        });
        btnKlinik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profil.this, ProfilKlinikActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profil.this);

                // Set the message show for the Alert time
                builder.setMessage("Kamu Yakin Ingin Keluar?");

                // Set Alert Title
                builder.setTitle("Alert !");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // When the user click yes button then app will close
                    LaunchActivity.clearStudentPref();
                    Intent logout = new Intent(getApplicationContext(), LaunchActivity.class);
                    startActivity(logout);
                    finish();
                });

                // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }
        });
    }

    /*
    this event will enable the back
    function to the button on press
     */
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
        Intent back = new Intent(Profil.this, BerandaActivity.class);
        startActivity(back);
    }
}
