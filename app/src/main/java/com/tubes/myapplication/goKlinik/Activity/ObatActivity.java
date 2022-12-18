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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Adapter.ListObatAdapter;
import com.tubes.myapplication.goKlinik.Model.GetObat;
import com.tubes.myapplication.goKlinik.Model.Obat;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObatActivity extends AppCompatActivity {
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    ImageView beli,reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat);

        String id_pasien = getIntent().getExtras().getString("id_pasien");

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_obat);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        beli = findViewById(R.id.btn_beli);
        beli.setOnClickListener(v -> {
            buy();
        });
        reset = findViewById(R.id.btn_reset);
        reset.setOnClickListener(v -> {
            reset();
        });
        ActionBar actionBar=getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daftar Obat");
        data();
    }
    public void data() {
        Call<GetObat> CoffeeCall = mApiInterface.getObat("get_obat");
        CoffeeCall.enqueue(new Callback<GetObat>() {
            @Override
            public void onResponse(Call<GetObat> call, Response<GetObat>
                    response) {
                List<Obat> dokterList = response.body().getListObat();
                Log.d("Retrofit Get", "Jumlah data obat: " +
                        String.valueOf(dokterList.size()));
                ListObatAdapter listDokterAdapter = new ListObatAdapter(dokterList);
                mRecyclerView.setAdapter(listDokterAdapter);

                //Onclick
                listDokterAdapter.setOnItemClickCallback(new ListObatAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Obat data) {
                        Log.d("obat","masuk dieu");
                    }
                });
            }
            @Override
            public void onFailure(Call<GetObat> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
    public void buy(){
        /*
        get data from recycleview
        send data to activity baru
         */
        String namaBeli = " ";
        namaBeli = ListObatAdapter.namaBeli;
        int total = 0;
        total = ListObatAdapter.total;
        if(namaBeli != " "){
            String id_pasien = getIntent().getExtras().getString("id_pasien");
            Intent intent = new Intent(ObatActivity.this, PesanObatActivity.class);
            intent.putExtra("namaBeli", namaBeli);
            intent.putExtra("total", total);
            intent.putExtra("id_pasien", id_pasien);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Belum ada obat yang dipilih", Toast.LENGTH_SHORT).show();
        }
    }
    public void reset(){
        ListObatAdapter.namaBeli = " ";
        ListObatAdapter.total = 0;
        data();
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
        Intent back = new Intent(ObatActivity.this, BerandaActivity.class);
        startActivity(back);
    }
}