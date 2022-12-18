package com.tubes.myapplication.goKlinik.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Activity.PesananActivity;
import com.tubes.myapplication.goKlinik.Activity.PesananBelum;
import com.tubes.myapplication.goKlinik.Config;
import com.tubes.myapplication.goKlinik.Model.Obat;
import com.tubes.myapplication.goKlinik.Model.Pesanan;
import com.tubes.myapplication.goKlinik.Model.PostPesanan;
import com.tubes.myapplication.goKlinik.Rest.ApiClient;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class ListPesananBelumAdapter extends RecyclerView.Adapter<ListPesananBelumAdapter.ListViewHolder> {
    public List<Pesanan> listPesanan;

    //Onclick
    private OnItemClickCallback onItemClickCallback;

    //Onclick Method
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListPesananBelumAdapter(List<Pesanan> list){
        listPesanan = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_pesanan_belum, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_total.setText("Total: Rp. " + listPesanan.get(position).getTotal());
        holder.tv_detail.setText("Detail Pesanan: " + listPesanan.get(position).getDetail());
        //mendapatkan suatu harga dari barang
        //Increment decrement

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listPesanan.get(holder.getAdapterPosition()));
            }
        });
        holder.btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_pengiriman = listPesanan.get(position).getIdPesanan();
                //Mengubah status pesanan
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

                Call<PostPesanan> SelesaiPesanan = mApiInterface.SelesaiPesanan("selesaiPesanan",RequestBody.create(MediaType.parse("text/plain"), id_pengiriman));
                SelesaiPesanan.enqueue(new Callback<PostPesanan>() {
                    @Override
                    public void onResponse(Call<PostPesanan> call, retrofit2.Response<PostPesanan> response) {

                        //refresh dan hapus
                        Intent intent = new Intent(view.getContext(), PesananActivity.class);

                        notifyItemRemoved(position);
                        Toast.makeText(view.getContext(), "Pesanan Selesai", Toast.LENGTH_SHORT).show();
                        view.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<PostPesanan> call, Throwable t) {
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

        TextView tv_detail,tv_total;
        Button btnSelesai;
        ApiInterface mApiInterface;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            tv_total = itemView.findViewById(R.id.tv_total);
            btnSelesai = itemView.findViewById(R.id.btn_selesai);
        }
    }

    //Onclick Interface
    public interface OnItemClickCallback {
        void onItemClicked(Pesanan data);
    }







}