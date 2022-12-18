package com.tubes.myapplication.goKlinik.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tubes.myapplication.R;
import com.tubes.myapplication.goKlinik.Model.Pesanan;

import java.util.List;

public class ListPesananSelesaiAdapter extends RecyclerView.Adapter<ListPesananSelesaiAdapter.ListViewHolder> {
    public List<Pesanan> listPesanan;

    //Onclick
    private OnItemClickCallback onItemClickCallback;

    //Onclick Method
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListPesananSelesaiAdapter(List<Pesanan> list) {
        listPesanan = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_pesanan_selesai, viewGroup, false);
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
    }

    @Override
    public int getItemCount() {
        return listPesanan.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_detail, tv_total;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            tv_total = itemView.findViewById(R.id.tv_total);
        }
    }

    //Onclick Interface
    public interface OnItemClickCallback {
        void onItemClicked(Pesanan data);
    }
}








