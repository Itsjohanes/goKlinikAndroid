package com.tubes.myapplication.goKlinik.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Model.GetKonsultasi;
import com.tubes.myapplication.goKlinik.Model.Konsultasi;
import com.tubes.myapplication.goKlinik.Model.PostKonsultasi;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingJadwalActivity extends AppCompatActivity {

    //ID Kirim Jadwal
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_NAMA = "extra_nama";
    public static final String EXTRA_JADWAL = "extra_jadwal";
    public static final String EXTRA_JAM = "extra_jam";
    private TextView tvid,tvjadwal,tvjam,tvNama;
    private Button btnBooking;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_jadwal);

        //calling action bar
        ActionBar actionBar=getSupportActionBar();

        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Booking Jadwal");

        tvjadwal = findViewById(R.id.tv_jadwal);
        tvjam = findViewById(R.id.tv_jam);
        btnBooking = findViewById(R.id.btn_booking);
        tvNama = findViewById(R.id.tv_namaDokter);

        String id = getIntent().getStringExtra(EXTRA_ID);
        String jadwal = getIntent().getStringExtra(EXTRA_JADWAL);
        String jam = getIntent().getStringExtra(EXTRA_JAM);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        tvjadwal.setText(jadwal);
        tvjam.setText(jam);
        tvNama.setText(getIntent().getStringExtra(EXTRA_NAMA));

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    private void saveData(){
        /*
        simpan data ke database
        ambil data dari form
        Sementara id_pasien manual diisi 2 dulu aja ye
        */
        try{
            String id_pasien = getIntent().getExtras().getString("id_pasien");
            String id_jadwal = getIntent().getStringExtra(EXTRA_ID);
            Call<PostKonsultasi> postHerosCall = mApiInterface.postNotes("insert_konsultasi",
                    RequestBody.create(MediaType.parse("text/plain"), getIntent().getStringExtra(EXTRA_ID)),
                    RequestBody.create(MediaType.parse("text/plain"), "0"),
                    RequestBody.create(MediaType.parse("text/plain"), id_pasien));
            postHerosCall.enqueue(new Callback<PostKonsultasi>() {
                @Override
                public void onResponse(Call<PostKonsultasi> call, Response<PostKonsultasi> response) {
                    String respon = response.body().getMessage();
                    Log.d("respon",respon);
                    if(!respon.equals("Antrian Penuh")) {

                        Call<GetKonsultasi> getKonsultasiCall = mApiInterface.getKonsultasiTerakhir("konfirmasi", id_pasien, id_jadwal);
                        getKonsultasiCall.enqueue(new Callback<GetKonsultasi>() {
                            @Override
                            //get status from restapi
                            public void onResponse(Call<GetKonsultasi> call, Response<GetKonsultasi> response) {

                                List<Konsultasi> notesList = response.body().getmNotes();
                                String id_konsultasi = notesList.get(0).getId_konsultasi();
                                String id_pasien = notesList.get(0).getId_pasien();
                                String id_jadwal = notesList.get(0).getId_jadwal();
                                String antrian = notesList.get(0).getAntrian();
                                String namaDokter = getIntent().getStringExtra(EXTRA_NAMA);
                                String jadwal = tvjadwal.getText().toString();
                                String jam = tvjam.getText().toString();

                                Intent intent = new Intent(BookingJadwalActivity.this, KonfirmasiKonsultasi.class);
                                intent.putExtra("id_konsultasi", id_konsultasi);
                                intent.putExtra("id_pasien", id_pasien);
                                intent.putExtra("id_jadwal", id_jadwal);
                                intent.putExtra("antrian", antrian);
                                intent.putExtra("namaDokter", namaDokter);
                                intent.putExtra("jadwal", jadwal);
                                intent.putExtra("jam", jam);
                                startActivity(intent);
                            }
                            @Override
                            public void onFailure(Call<GetKonsultasi> call, Throwable t) {
                                Log.e("Retrofit Get", t.toString());
                            }
                        });
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(BookingJadwalActivity.this);
                        // Set the message show for the Alert time
                        builder.setMessage("Mohon Maaf Antrian Sudah Penuh");
                        // Set Alert Title
                        builder.setTitle("Gagal");
                        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                        builder.setCancelable(false);
                        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                            // When the user click yes button then app will close
                            LaunchActivity.clearStudentPref();
                            Intent logout = new Intent(BookingJadwalActivity.this, BerandaActivity.class);
                            startActivity(logout);
                        });
                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();
                        // Show the Alert Dialog box
                        alertDialog.show();
                    }
                }
                @Override
                public void onFailure(Call<PostKonsultasi> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    Log.d("RETRO", "ON FAILURE : " + t.getCause());
                    Toast.makeText(getApplicationContext(), "Error, entry data", Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
        }
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
        Intent back = new Intent(BookingJadwalActivity.this, DetailDokterActivity.class);
        startActivity(back);
    }
}