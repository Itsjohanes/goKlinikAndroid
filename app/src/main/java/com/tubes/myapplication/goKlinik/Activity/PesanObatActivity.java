package com.tubes.myapplication.goKlinik.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Adapter.ListObatAdapter;
import com.tubes.myapplication.goKlinik.Model.PostPesanan;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesanObatActivity extends AppCompatActivity {
    TextView tvResult,tvTotal;
    Button btnPesan;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_obat);

        String id_pasien = getIntent().getExtras().getString("id_pasien");

        tvResult = findViewById(R.id.tv_nama);
        tvTotal = findViewById(R.id.tv_total);
        String namaBeli = getIntent().getStringExtra("namaBeli");
        tvResult.setText(namaBeli);
        int total = getIntent().getIntExtra("total",0);
        tvTotal.setText(String.valueOf(total));
        btnPesan = findViewById(R.id.btn_pesan);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnPesan.setOnClickListener(v -> {
            pesan(id_pasien);
        });
    }
    public void pesan(String id_pasien){
        //sementara dibuat ID pasiennya 5
        try{
            Call<PostPesanan> postHerosCall = mApiInterface.postPesanan("insert_pesanan",
                    RequestBody.create(MediaType.parse("text/plain"), id_pasien),
                    RequestBody.create(MediaType.parse("text/plain"), "0"),
                    RequestBody.create(MediaType.parse("text/plain"), tvResult.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), tvTotal.getText().toString()));
            postHerosCall.enqueue(new Callback<PostPesanan>() {
                @Override
                public void onResponse(Call<PostPesanan> call, Response<PostPesanan> response) {
                    Toast.makeText(getApplicationContext(), "Berhasil dipesan", Toast.LENGTH_LONG).show();
                    ListObatAdapter.namaBeli = " ";
                    ListObatAdapter.total = 0;

                    finish();
                }
                @Override
                public void onFailure(Call<PostPesanan> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    Log.d("RETRO", "ON FAILURE : " + t.getCause());
                    Toast.makeText(getApplicationContext(), "Error, entry data", Toast.LENGTH_LONG).show();
                    ListObatAdapter.namaBeli = " ";
                    ListObatAdapter.total = 0;
                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }
    @Override
    public void onBackPressed() {
        /*
        this is only needed if you have specific things
        that you want to do when the user presses the back button.
        */
        super.onBackPressed();
        ListObatAdapter.namaBeli = " ";
        ListObatAdapter.total = 0;
    }
}