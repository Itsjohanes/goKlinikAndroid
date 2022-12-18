package com.tubes.myapplication.goKlinik.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Model.GetPasien;
import com.tubes.myapplication.goKlinik.Model.Pasien;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;




import java.util.List;

public class MasukActivity extends AppCompatActivity{
    private EditText edtUser, edtPassword;
    private Button btnLogin;
    ApiInterface mApiInterface;
    private AppCompatCheckBox cbPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        //calling action bar
        ActionBar actionBar=getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Masuk");

        edtUser = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        cbPass = (AppCompatCheckBox)findViewById(R.id.cb_pass);
        btnLogin = findViewById(R.id.btn_masuk);

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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyStudent();
            }
        });
    }

    private void verifyStudent(){
        String username  = edtUser.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        //Server Call
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetPasien> HerosCall = mApiInterface.getakun("login_pasien",username,password);
        final String finalPassword = password;

        HerosCall.enqueue(new Callback<GetPasien>() {
            @Override
            public void onResponse(Call<GetPasien> call, Response<GetPasien>
                    response) {
                List<Pasien> notesList = response.body().getListDataNotes();

                if(notesList.size() > 0){
                    String id_pasien =  notesList.get(0).getId();
                    String username = notesList.get(0).getUsername();
                    String password = notesList.get(0).getPassword();
                    String nama = notesList.get(0).getNama();
                    String tgl_lahir = notesList.get(0).getTgl_lahir();
                    String jenkel = notesList.get(0).getJenkel();
                    String no_hp = notesList.get(0).getNo_hp();
                    String alamat = notesList.get(0).getAlamat();

                    Intent intent = new Intent(MasukActivity.this, BerandaActivity.class);
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

            }
            //ketika login gagal
            @Override
            public void onFailure(Call<GetPasien> call, Throwable t) {
                Toast.makeText(MasukActivity.this,"Username atau Password Salah!",Toast.LENGTH_SHORT).show();
                //Log.e("Retrofit Get", t.toString());
                showAlertError();
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
        Intent back = new Intent(MasukActivity.this, LaunchActivity.class);
        startActivity(back);
    }
    public void showAlertError(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MasukActivity.this);
        // Set the message show for the Alert time
        builder.setMessage("Silahkan Ulangi Kembali atau Daftar Akun");
        // Set Alert Title
        builder.setTitle("Username atau Password Salah!");
        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
           dialog.dismiss();
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }
}
