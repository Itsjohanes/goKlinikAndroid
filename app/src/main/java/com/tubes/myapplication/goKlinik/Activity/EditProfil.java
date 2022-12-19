package com.tubes.myapplication.goKlinik.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Model.GetPasien;
import com.tubes.myapplication.goKlinik.Model.Pasien;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfil extends AppCompatActivity {
    private AppCompatCheckBox cbPass;
    private EditText edtUsername, edtPassword, edtNama, edtTglLahir, edtNoHP, edtAlamat;
    private RadioGroup rgJenkel;
    private RadioButton rbWanita, rbPria;
    ApiInterface mApiInterface;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        //calling action bar
        String id_pasien = getIntent().getExtras().getString("id_pasien");
        String username = getIntent().getExtras().getString("username");
        String password = getIntent().getExtras().getString("password");
        String nama = getIntent().getExtras().getString("nama");
        String tgl_lahir = getIntent().getExtras().getString("tgl_lahir");
        String jenkel = getIntent().getExtras().getString("jenkel");
        String no_hp = getIntent().getExtras().getString("no_hp");
        String alamat = getIntent().getExtras().getString("alamat");

        ActionBar actionBar=getSupportActionBar();

        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ubah Profil");

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        //Komponen Form
        edtUsername = findViewById(R.id.edt_username);
        cbPass = (AppCompatCheckBox)findViewById(R.id.cb_pass);
        edtPassword=findViewById(R.id.edt_password);
        edtNama = findViewById(R.id.edt_nama);
        edtTglLahir = findViewById(R.id.edt_tgl_lahir);
        rgJenkel = findViewById(R.id.rg_jenkel);
        rbWanita = findViewById(R.id.rb_wanita);
        rbPria = findViewById(R.id.rb_pria);
        edtNoHP = findViewById(R.id.edt_nohp);
        edtAlamat = findViewById(R.id.edt_alamat);
        btnSimpan= findViewById(R.id.btn_simpan);

        edtUsername.setText(username);
        edtPassword.setText(password);
        edtNama.setText(nama);
        edtTglLahir.setText(tgl_lahir);
        if(jenkel.equals("Wanita")){
            rbWanita.setChecked(true);
        }else{
            rbPria.setChecked(true);
        }
        edtNoHP.setText(no_hp);
        edtAlamat.setText(alamat);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Pasien> putHerosCall = mApiInterface.update_pasien("update_pasien", id_pasien,
                        username,
                        RequestBody.create(MediaType.parse("text/plain"), edtUsername.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edtPassword.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edtNama.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edtTglLahir.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), jenkel),
                        RequestBody.create(MediaType.parse("text/plain"), edtNoHP.getText().toString()),
                        RequestBody.create(MediaType.parse("text/plain"), edtAlamat.getText().toString())
                );
                putHerosCall.enqueue(new Callback<Pasien>() {
                    @Override
                    public void onResponse(Call<Pasien> call, Response<Pasien> response) {
                        String message = response.body().getMessage();
                        if(!message.equals("Username Sudah Ada") || edtUsername.getText().toString().equals(username)){


                            AlertDialog.Builder builder = new AlertDialog.Builder(EditProfil.this);
                            // Set the message show for the Alert time
                            builder.setMessage("Mohon Login Ulang!");
                            // Set Alert Title
                            builder.setTitle("Edit Data Profil Berhasil");
                            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                            builder.setCancelable(false);

                            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                                // When the user click yes button then app will close
                                Intent intent = new Intent(EditProfil.this, MasukActivity.class);
                                startActivity(intent);
                                finish();
                            });
                            // Create the Alert dialog
                            AlertDialog alertDialog = builder.create();
                            // Show the Alert Dialog box
                            alertDialog.show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Username Sudah Ada", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pasien> call, Throwable t) {
                        Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                        Log.d("RETRO", "ON FAILURE : " + t.getCause());
                        Toast.makeText(getApplicationContext(), "Error, update data", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
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
        Intent back = new Intent(EditProfil.this, Profil.class);
        startActivity(back);
    }
}