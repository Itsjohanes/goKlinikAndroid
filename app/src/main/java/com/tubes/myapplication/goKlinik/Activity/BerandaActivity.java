package com.tubes.myapplication.goKlinik.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Adapter.ListDokterAdapter;
import com.tubes.myapplication.goKlinik.Model.Dokter;
import com.tubes.myapplication.goKlinik.Model.GetDokter;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaActivity extends AppCompatActivity {
    private TextView tvUsername;
    private ImageView btnProfil, btnApotek;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        String id_pasien = getIntent().getExtras().getString("id_pasien");
        String username = getIntent().getExtras().getString("username");
        String password = getIntent().getExtras().getString("password");
        String nama = getIntent().getExtras().getString("nama");
        String tgl_lahir = getIntent().getExtras().getString("tgl_lahir");
        String jenkel = getIntent().getExtras().getString("jenkel");
        String no_hp = getIntent().getExtras().getString("no_hp");
        String alamat = getIntent().getExtras().getString("alamat");

        tvUsername=findViewById(R.id.tv_username);
        tvUsername.setText(nama);
        btnProfil=findViewById(R.id.ic_profil);
        btnApotek=findViewById(R.id.ic_apotek);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_dokter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        firebase();
        data();

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, Profil.class);
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
        btnApotek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, ObatActivity.class);
                intent.putExtra("id_pasien", id_pasien);
                startActivity(intent);
            }
        });
    }
    public void data() {
        Call<GetDokter> CoffeeCall = mApiInterface.getCoffee("get_dokter");
        CoffeeCall.enqueue(new Callback<GetDokter>() {
            @Override
            public void onResponse(Call<GetDokter> call, Response<GetDokter>
                    response) {
                List<Dokter> dokterList = response.body().getListDokter();
                Log.d("Retrofit Get", "Jumlah data dokter: " +
                        String.valueOf(dokterList.size()));
                ListDokterAdapter listDokterAdapter = new ListDokterAdapter(dokterList);
                mRecyclerView.setAdapter(listDokterAdapter);

                //Onclick
                listDokterAdapter.setOnItemClickCallback(new ListDokterAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Dokter data) {
                        showSelectedCoffeeDrink(data);
                    }
                });
            }
            @Override
            public void onFailure(Call<GetDokter> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    //Onclick method
    private void showSelectedCoffeeDrink(Dokter dokter){
        Intent kirimData = new Intent(BerandaActivity.this, DetailDokterActivity.class);
        kirimData.putExtra(DetailDokterActivity.EXTRA_NAMA, dokter.getNama_dokter());
        kirimData.putExtra(DetailDokterActivity.EXTRA_ID,dokter.getId());
        String id_pasien = getIntent().getExtras().getString("id_pasien");
        kirimData.putExtra("id_pasien", id_pasien);
        kirimData.putExtra(DetailDokterActivity.EXTRA_SPESIALIS, dokter.getSpesialis());
        kirimData.putExtra(DetailDokterActivity.EXTRA_IMGDOKTER, dokter.getFoto_dokter());
        kirimData.putExtra(DetailDokterActivity.EXTRA_KETERANGAN,dokter.getKeterangan());
        startActivity(kirimData);
    }
    public void firebase(){
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        String msg = getString(R.string.msg_subscribed);
        Log.d(TAG, msg);
        Toast.makeText(BerandaActivity.this, msg, Toast.LENGTH_SHORT).show();
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(BerandaActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.d("newToken", newToken);
            }
        });
    }
}