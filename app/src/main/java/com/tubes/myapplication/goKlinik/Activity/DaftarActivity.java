package com.tubes.myapplication.goKlinik.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Model.Pasien;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity {
    private AppCompatCheckBox cbPass;
    private EditText edtUsername, edtPassword, edtNama, edtTglLahir, edtNoHP, edtAlamat;
    private RadioGroup rgJenkel;
    ApiInterface mApiInterface;
    private Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        //Komponen Form
        edtUsername = findViewById(R.id.edt_username);
        cbPass = (AppCompatCheckBox)findViewById(R.id.cb_pass);
        edtPassword=findViewById(R.id.edt_password);
        edtNama = findViewById(R.id.edt_nama);
        edtTglLahir = findViewById(R.id.edt_tgl_lahir);
        rgJenkel = findViewById(R.id.rg_jenkel);
        edtNoHP = findViewById(R.id.edt_nohp);
        edtAlamat = findViewById(R.id.edt_alamat);
        btnDaftar=findViewById(R.id.btn_daftar);

        //calling action bar
        ActionBar actionBar=getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daftar Akun");

        //Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        cbPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(!isChecked){
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        //fungsi simpan
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    private void saveData(){
        Integer selectedID = rgJenkel.getCheckedRadioButtonId();
        String jenkel="";
        switch (selectedID){
            case R.id.rb_pria:
                jenkel="Pria";
                break;
            case R.id.rb_wanita:
                jenkel="Wanita";
                break;
        }
        Call<Pasien> postHerosCall = mApiInterface.insert_pasien("insert_pasien",
                RequestBody.create(MediaType.parse("text/plain"), edtUsername.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), edtPassword.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), edtNama.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), edtTglLahir.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), jenkel),
                RequestBody.create(MediaType.parse("text/plain"), edtNoHP.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), edtAlamat.getText().toString())
                );

        postHerosCall.enqueue(new Callback<Pasien>() {
            @Override
            public void onResponse(Call<Pasien> call, Response<Pasien> response) {

                String message = response.body().getMessage();
                if (!message.equals("Username Sudah Ada")) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(DaftarActivity.this);
                    // Set the message show for the Alert time

                    builder.setMessage("Silahkan Lanjutkan Login/Masuk!");
                    // Set Alert Title
                    builder.setTitle("Daftar Akun Berhasil");
                    // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                    builder.setCancelable(false);

                    // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // When the user click yes button then app will close
                        Intent intent = new Intent(DaftarActivity.this, MasukActivity.class);
                        startActivity(intent);
                        finish();
                    });
                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                }else{
                    //buat kalo data sudah ada
                    AlertDialog.Builder builder = new AlertDialog.Builder(DaftarActivity.this);
                    // Set the message show for the Alert time

                    builder.setMessage("Username sudah terdaftar!");
                    // Set Alert Title
                    builder.setTitle("Daftar Akun Gagal");
                    // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                    builder.setCancelable(false);

                    // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // When the user click yes button then app will close
                        Intent intent = new Intent(DaftarActivity.this, MasukActivity.class);
                        startActivity(intent);
                        finish();
                    });
                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                }
            }


            @Override
            public void onFailure(Call<Pasien> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Log.d("RETRO", "ON FAILURE : " + t.getCause());
                Toast.makeText(getApplicationContext(), "Error, entry data", Toast.LENGTH_LONG).show();
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
        Intent back = new Intent(DaftarActivity.this, LaunchActivity.class);
        startActivity(back);
    }
}
