package com.tubes.myapplication.goKlinik.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Activity.KonsultasiActivity;
import com.tubes.myapplication.goKlinik.Activity.KonsultasiSelesai;
import com.tubes.myapplication.goKlinik.Activity.PesananActivity;
import com.tubes.myapplication.goKlinik.Model.Konsultasi2;
import com.tubes.myapplication.goKlinik.Model.PostKonsultasi;
import com.tubes.myapplication.goKlinik.Model.PostPesanan;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class ListKonsultasiBelumAdapter  extends RecyclerView.Adapter<ListKonsultasiBelumAdapter.ListViewHolder>{

    public List<Konsultasi2> listPesanan;

    //Onclick
    private ListKonsultasiBelumAdapter.OnItemClickCallback onItemClickCallback;

    //Onclick Method
    public void setOnItemClickCallback(ListKonsultasiBelumAdapter.OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListKonsultasiBelumAdapter(List<Konsultasi2> list){
        listPesanan = list;
    }

    @NonNull
    @Override
    public ListKonsultasiBelumAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_konsultasi_belum, viewGroup, false);
        return new ListKonsultasiBelumAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListKonsultasiBelumAdapter.ListViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_namaDokter.setText( listPesanan.get(position).getNama_dokter());
        holder.tv_jadwal.setText(listPesanan.get(position).getJadwal());
        holder.tv_antrian.setText("Antrian ke-" +listPesanan.get(position).getAntrian());
        holder.tv_jam.setText(listPesanan.get(position).getJam());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listPesanan.get(holder.getAdapterPosition()));
            }
        });

        holder.btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_konsultasi = listPesanan.get(position).getId_konsultasi();
                //Mengubah status pesanan
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                Call<PostKonsultasi> SelesaiKonsultasi = mApiInterface.selesaiKonsultasi("selesai", RequestBody.create(MediaType.parse("text/plain"), id_konsultasi));
                SelesaiKonsultasi.enqueue(new Callback<PostKonsultasi>() {
                    @Override
                    public void onResponse(Call<PostKonsultasi> call, retrofit2.Response<PostKonsultasi> response) {

                        //refresh dan hapus
                        Intent intent = new Intent(view.getContext(), KonsultasiActivity.class);

                        notifyItemRemoved(position);
                        Toast.makeText(view.getContext(), "Pesanan Selesai", Toast.LENGTH_SHORT).show();
                        view.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<PostKonsultasi> call, Throwable t) {
                        Toast.makeText(view.getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPesanan.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_namaDokter,tv_jadwal,tv_jam,tv_antrian;
        Button btnSelesai;
        ApiInterface mApiInterface;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_namaDokter  = itemView.findViewById(R.id.tv_namaDokter);
            tv_jadwal = itemView.findViewById(R.id.tv_jadwal);
            tv_jam = itemView.findViewById(R.id.tv_jam);
            tv_antrian = itemView.findViewById(R.id.tv_antrian);
            btnSelesai = itemView.findViewById(R.id.btn_selesai);

        }
    }

    //Onclick Interface
    public interface OnItemClickCallback {
        void onItemClicked(Konsultasi2 data);
    }
}
