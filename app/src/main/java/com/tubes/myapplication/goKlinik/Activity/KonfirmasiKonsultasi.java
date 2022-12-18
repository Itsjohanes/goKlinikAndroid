package com.tubes.myapplication.goKlinik.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Model.PostKonsultasi;
import com.tubes.myapplication.goKlinik.Model.PostKonsultasi;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Part;

public class KonfirmasiKonsultasi extends AppCompatActivity {

    Button btnKonfirmasi,btnBatal;
    TextView tvNama,tvJadwal, tvJam, tvAntrian;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_konsultasi);
        btnKonfirmasi = findViewById(R.id.btn_konfirmasi);
        btnBatal = findViewById(R.id.btn_batal);
        tvNama = findViewById(R.id.tv_namaDokter);
        tvAntrian = findViewById(R.id.tv_antrian);
        tvJadwal = findViewById(R.id.tv_jadwal);
        tvJam = findViewById(R.id.tv_jam);

        //Data Sensitif
        String id_konsultasi = getIntent().getStringExtra("id_konsultasi");
        String id_pasien = getIntent().getStringExtra("id_pasien");
        String id_dokter = getIntent().getStringExtra("id_dokter");
        String id_jadwal = getIntent().getStringExtra("id_jadwal");
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        //Buat ditampilkan
        String jam = getIntent().getStringExtra("jam");
        String jadwal = getIntent().getStringExtra("jadwal");
        String namaDokter = getIntent().getStringExtra("namaDokter");
        String antrian = getIntent().getStringExtra("antrian");

        tvNama.setText(namaDokter);
        tvAntrian.setText("Antrian ke- " +antrian);
        tvJadwal.setText(jadwal);
        tvJam.setText(jam);
        btnKonfirmasi.setOnClickListener(v -> {
           kembali();
        });
        btnBatal.setOnClickListener(v -> {
            batal();
        });
    }
    //Kembali
    public void kembali(){
        AlertDialog.Builder builder = new AlertDialog.Builder(KonfirmasiKonsultasi.this);
        // Set the message show for the Alert time
        builder.setMessage("Mohon Tunggu Konfirmasi WhatsApp dari Pihak Kami");
        // Set Alert Title
        builder.setTitle("Berhasil");
        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);
        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
    public void batal(){
        //Akan diremove berdasarkan id_konsultasi
        String id_konsultasi = getIntent().getStringExtra("id_konsultasi");
        Call<PostKonsultasi> deleteKonsultasiCall = mApiInterface.DeleteKonsultasi("batalkan", RequestBody.create(MediaType.parse("text/plain"), id_konsultasi));
        //Jika sukses
        deleteKonsultasiCall.enqueue(new retrofit2.Callback<PostKonsultasi>() {
            @Override
            public void onResponse(Call<PostKonsultasi> call, retrofit2.Response<PostKonsultasi> response) {
                Toast.makeText(getApplicationContext(), "Konsultasi Dibatalkan", Toast.LENGTH_LONG).show();
                Log.d("Delete Konsultasi", response.body().getStatus());
                finish();
            }
            @Override
            public void onFailure(Call<PostKonsultasi> call, Throwable t) {
                Log.d("Delete Konsultasi", t.getMessage());
            }
        });
    }
}