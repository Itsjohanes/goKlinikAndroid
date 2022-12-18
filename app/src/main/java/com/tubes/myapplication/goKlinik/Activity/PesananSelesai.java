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
import com.tubes.myapplication.goKlinik.Adapter.ListPesananBelumAdapter;
import com.tubes.myapplication.goKlinik.Adapter.ListPesananSelesaiAdapter;
import com.tubes.myapplication.goKlinik.Model.GetPesanan;
import com.tubes.myapplication.goKlinik.Model.Pesanan;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananSelesai extends AppCompatActivity {
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_selesai);
        //fetch data from intent
        String id_pasien = getIntent().getExtras().getString("id_pasien");
        String username = getIntent().getExtras().getString("username");
        String password = getIntent().getExtras().getString("password");
        String nama = getIntent().getExtras().getString("nama");
        String tgl_lahir = getIntent().getExtras().getString("tgl_lahir");
        String jenkel = getIntent().getExtras().getString("jenkel");
        String no_hp = getIntent().getExtras().getString("no_hp");
        String alamat = getIntent().getExtras().getString("alamat");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_selesai);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        ActionBar actionBar=getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pesanan Selesai");
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
        Intent back = new Intent(PesananSelesai.this, PesananActivity.class);
        startActivity(back);
    }
    public void data(String id_pasien) {
        Call<GetPesanan> CoffeeCall = mApiInterface.getPesanan("select_pesanan_selesai",id_pasien);
        CoffeeCall.enqueue(new Callback<GetPesanan>() {
            @Override
            public void onResponse(Call<GetPesanan> call, Response<GetPesanan>
                    response) {
                try{
                    List<Pesanan> dokterList = response.body().getListPesanan();
                    Log.d("Retrofit Get", "Jumlah data Pesanan: " +
                            String.valueOf(dokterList.size()));
                    ListPesananSelesaiAdapter listDokterAdapter = new ListPesananSelesaiAdapter(dokterList);
                    mRecyclerView.setAdapter(listDokterAdapter);

                    //Onclick
                    listDokterAdapter.setOnItemClickCallback(new ListPesananSelesaiAdapter.OnItemClickCallback() {
                        @Override
                        public void onItemClicked(com.tubes.myapplication.goKlinik.Model.Pesanan data) {
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
            public void onFailure(Call<GetPesanan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
