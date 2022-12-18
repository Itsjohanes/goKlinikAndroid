package com.tubes.myapplication.goKlinik.Rest;

import com.tubes.myapplication.goKlinik.Model.GetDokter;
import com.tubes.myapplication.goKlinik.Model.GetJadwal;
import com.tubes.myapplication.goKlinik.Model.GetKonsultasi;
import com.tubes.myapplication.goKlinik.Model.GetKonsultasi2;
import com.tubes.myapplication.goKlinik.Model.GetKonsultasi3;
import com.tubes.myapplication.goKlinik.Model.GetObat;
import com.tubes.myapplication.goKlinik.Model.GetPasien;
import com.tubes.myapplication.goKlinik.Model.GetPesanan;
import com.tubes.myapplication.goKlinik.Model.Pasien;
import com.tubes.myapplication.goKlinik.Model.PostKonsultasi;
import com.tubes.myapplication.goKlinik.Model.PostPesanan;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    //Login
    @GET("restapi_pasien.php")
    Call<GetPasien> getakun(@Query("function") String function,
                            @Query("username") String username,
                            @Query("password") String password);
    //ByID
    @GET("restapi_pasien.php")
    Call<GetPasien> getIdPasien(@Query("function") String function,
                            @Query("id_pasien") String id_pasien);

    //Insert data ke tabel melalui API
    @Multipart
    @POST("restapi_pasien.php")
    Call<Pasien> insert_pasien(@Query("function") String function,
                                  @Part("username") RequestBody username,
                                  @Part("password") RequestBody password,
                                  @Part("nama") RequestBody nama,
                                  @Part("tgl_lahir") RequestBody tgl_lahir,
                                  @Part("jenkel") RequestBody jenkel,
                                  @Part("no_hp") RequestBody no_hp,
                                  @Part("alamat") RequestBody alamat);
    //Query mengirim lewat URL via Get, Part dikirim lewat POST
    //Update data ke tabel melalui API
    @Multipart
    @POST("restapi_pasien.php")
    Call<Pasien> update_pasien(@Query("function") String function,
                                        @Query("id_pasien") String id_pasien,
                                        @Part("username") RequestBody username,
                                        @Part("password") RequestBody password,
                                        @Part("nama") RequestBody nama,
                                        @Part("tgl_lahir") RequestBody tgl_lahir,
                                        @Part("jenkel") RequestBody jenkel,
                                        @Part("no_hp") RequestBody no_hp,
                                        @Part("alamat") RequestBody alamat);

    @GET("restapi_dokter.php")
    Call<GetDokter> getCoffee(@Query("function") String function);

    @GET("restapi_dokter.php")
    Call<GetJadwal> getJadwal(@Query("function") String function,
                              @Query("id_dokter") String id_dokter);
    //Insert Konsultasi
    @Multipart
    @POST("restapi_konsultasi.php")
    Call<PostKonsultasi> postNotes(@Query("function") String function,
                                   @Part("id_jadwal") RequestBody id_jadwal,
                                   @Part("status") RequestBody status,
                                   @Part("id_pasien") RequestBody id_pasien);
    @GET("restapi_obat.php")
    Call<GetObat> getObat(@Query("function") String function);


    //restapi untuk pesanan obat
    @Multipart
    @POST("restapi_pesanan.php")
    Call<PostPesanan> postPesanan(
            @Query("function") String function,
            @Part("id_pasien") RequestBody id_pasien,
            @Part("status") RequestBody status,
            @Part("detail") RequestBody detail,
            @Part("total") RequestBody total
    );

    //restapi untuk pesanan obat by ID
    @GET("restapi_pesanan.php")
    Call<GetPesanan> getPesanan(@Query("function") String function,
                                @Query("id_pasien") String id_pasien);


    //mendapat data Konsultasi Terakhir
    @GET("restapi_konsultasi.php")
    Call<GetKonsultasi> getKonsultasiTerakhir(@Query("function") String function,
                                              @Query("id_pasien") String id_konsultasi,
                                              @Query("id_jadwal") String id_jadwal);

    //untuk mendapatkan data konsultasi yang belum, selesai pada minggu atau rentang waktu tertentu
    @GET("restapi_konsultasi.php")
    Call<GetKonsultasi2> getKonsultasi(@Query("function") String function,
                                       @Query("id_pasien") String id_pasien
                                      );
    //untuk mendapatkan data konsultasi di masa lampau
    @GET("restapi_konsultasi.php")
    Call<GetKonsultasi3> getKonsultasiLampau(@Query("function") String function,
                                             @Query("id_pasien") String id_pasien
    );

    //Membatalkan Konsultasi
    @Multipart
    @POST("restapi_konsultasi.php")
    Call<PostKonsultasi> DeleteKonsultasi(@Query("function") String function,
                                         @Part("id_konsultasi") RequestBody id_konsultasi
                                      );
    //selesai konsultasi
    @Multipart
    @POST("restapi_konsultasi.php")
    Call<PostKonsultasi> selesaiKonsultasi(@Query("function") String function,
                                         @Part("id_konsultasi") RequestBody id_konsultasi
    );
    //Menyelesaikan Pesanan
    @Multipart
    @POST("restapi_pesanan.php")
    Call<PostPesanan> SelesaiPesanan(@Query("function") String function,
                                     @Part("id_pengiriman") RequestBody id_pengiriman
    );

}
