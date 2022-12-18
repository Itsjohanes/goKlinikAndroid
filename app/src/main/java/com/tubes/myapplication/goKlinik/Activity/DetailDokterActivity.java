package com.tubes.myapplication.goKlinik.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Adapter.ListJadwalAdapter;
import com.tubes.myapplication.goKlinik.Config;
import com.tubes.myapplication.goKlinik.Model.GetJadwal;
import com.tubes.myapplication.goKlinik.Model.Jadwal;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.tubes.myapplication.R;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDokterActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_NAMA = "extra_nama";
    public static final String EXTRA_IMGDOKTER = "extra_imageposter";
    public static final String EXTRA_SPESIALIS = "extra_spesialis";
    public static final String EXTRA_KETERANGAN = "extra_keterangan";

    private TextView tvName, tvSpesialis, tvKeterangan;
    ImageView imgPoster;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);

        //calling action bar
        ActionBar actionBar=getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Dokter");

        tvName = findViewById(R.id.tv_name);
        tvSpesialis = findViewById(R.id.tv_spesialis);
        tvKeterangan = findViewById(R.id.tv_keterangan);
        imgPoster = findViewById(R.id.img_dokter);
        tvName = findViewById(R.id.tv_name);
        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        String spesialis = getIntent().getStringExtra(EXTRA_SPESIALIS);
        String imgPosterFile = getIntent().getStringExtra(EXTRA_IMGDOKTER);
        String keterangan = getIntent().getStringExtra(EXTRA_KETERANGAN);
        //mendapat ID dokter
        String id = getIntent().getStringExtra(EXTRA_ID);

        tvName.setText(nama);
        tvSpesialis.setText(spesialis);
        tvKeterangan.setText(keterangan);
        Glide.with(DetailDokterActivity.this)
                .load(Config.IMAGES_URL + imgPosterFile)
                .apply(new RequestOptions().placeholder(R.color.black))
                .into(imgPoster);

        //Create RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_jadwal);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //panggil API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        jadwal();

    }

    public void jadwal() {
        String id_dokter = getIntent().getStringExtra(EXTRA_ID);
        Call<GetJadwal> dokterCall = mApiInterface.getJadwal("getjadwaldokter_byiddokter", id_dokter);
        dokterCall.enqueue(new Callback<GetJadwal>() {
            @Override
            public void onResponse(Call<GetJadwal> call, Response<GetJadwal>
                    response) {
                List<Jadwal> DokterList = new ArrayList<>();
                DokterList = response.body().getListjadwal();
                //Pakai trycatch kalau dia null maka recycleview akan dihapus
                try {
                    if (DokterList.size() > 0) {
                        ListJadwalAdapter listJadwalAdapter = new ListJadwalAdapter(DokterList);
                        mRecyclerView.setAdapter(listJadwalAdapter);
                        listJadwalAdapter.setOnItemClickCallback(new ListJadwalAdapter.OnItemClickCallback() {
                            @Override
                            public void onItemClicked(Jadwal data) {
                                showSelectedData(data);
                            }
                        });
                    }
                } catch (Exception e) {
                    mRecyclerView.setVisibility(android.view.View.GONE);
                }
            }
            @Override
            public void onFailure(Call<GetJadwal> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
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
        Intent back = new Intent(DetailDokterActivity.this, BerandaActivity.class);
        startActivity(back);
    }
    private void showSelectedData(Jadwal jadwal) {
        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        Intent kirimData = new Intent(DetailDokterActivity.this, BookingJadwalActivity.class);
        kirimData.putExtra(BookingJadwalActivity.EXTRA_JADWAL, jadwal.getJadwal());
        kirimData.putExtra(BookingJadwalActivity.EXTRA_ID,jadwal.getId());
        Log.d("id",jadwal.getId());
        String id_pasien = getIntent().getExtras().getString("id_pasien");
        kirimData.putExtra("id_pasien",id_pasien);
        kirimData.putExtra(BookingJadwalActivity.EXTRA_JAM, jadwal.getJam());
        kirimData.putExtra(BookingJadwalActivity.EXTRA_NAMA, nama);
        startActivity(kirimData);
    }
}