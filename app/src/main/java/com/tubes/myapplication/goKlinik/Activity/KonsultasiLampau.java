package com.tubes.myapplication.goKlinik.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Adapter.ListKonsultasiLampauAdapter;
import com.tubes.myapplication.goKlinik.Adapter.ListKonsultasiSelesaiAdapter;
import com.tubes.myapplication.goKlinik.Model.GetKonsultasi2;
import com.tubes.myapplication.goKlinik.Model.GetKonsultasi3;
import com.tubes.myapplication.goKlinik.Model.Konsultasi2;
import com.tubes.myapplication.goKlinik.Model.Konsultasi3;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsultasiLampau extends AppCompatActivity {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi_lampau);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_lampau);

        //ambil semua data intent
        String id_pasien = getIntent().getExtras().getString("id_pasien");
        String username = getIntent().getExtras().getString("username");
        String password = getIntent().getExtras().getString("password");
        String nama = getIntent().getExtras().getString("nama");
        String tgl_lahir = getIntent().getExtras().getString("tgl_lahir");
        String jenkel = getIntent().getExtras().getString("jenkel");
        String no_hp = getIntent().getExtras().getString("no_hp");
        String alamat = getIntent().getExtras().getString("alamat");

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ActionBar actionBar=getSupportActionBar();

        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Konsultasi Lampau");
        data(id_pasien);
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
        Intent back = new Intent(KonsultasiLampau.this, KonsultasiActivity.class);
        startActivity(back);
    }
    public void data(String id_pasien) {
        Call<GetKonsultasi3> CoffeeCall = mApiInterface.getKonsultasiLampau("getKonsultasiSudahLampau",id_pasien);
        CoffeeCall.enqueue(new Callback<GetKonsultasi3>() {
            @Override
            public void onResponse(Call<GetKonsultasi3> call, Response<GetKonsultasi3>
                    response) {
                try{
                    List<Konsultasi3> konsultasi3List = response.body().getmNotes();
                    Log.d("Retrofit Get", "Jumlah data Pesanan: " +
                            String.valueOf(konsultasi3List.size()));
                    ListKonsultasiLampauAdapter listDokterAdapter = new ListKonsultasiLampauAdapter(konsultasi3List);
                    mRecyclerView.setAdapter(listDokterAdapter);

                    //Onclick
                    listDokterAdapter.setOnItemClickCallback(new ListKonsultasiLampauAdapter.OnItemClickCallback() {
                        @Override
                        public void onItemClicked(Konsultasi3 data) {
                            Log.d("Pesanan","masuk dieu");
                        }
                    });
                }catch (Exception e){
                    //print exception
                    e.printStackTrace();
                    Log.d("id_pasien",id_pasien);
                }
            }
            @Override
            public void onFailure(Call<GetKonsultasi3> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}