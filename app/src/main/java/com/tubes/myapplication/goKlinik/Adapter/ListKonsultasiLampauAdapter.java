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
import com.tubes.myapplication.goKlinik.Model.Konsultasi2;
import com.tubes.myapplication.goKlinik.Model.Konsultasi3;
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import java.util.List;

public class ListKonsultasiLampauAdapter extends RecyclerView.Adapter<ListKonsultasiLampauAdapter.ListViewHolder>{
    public List<Konsultasi3> listPesanan;

    //Onclick
    private ListKonsultasiLampauAdapter.OnItemClickCallback onItemClickCallback;

    //Onclick Method
    public void setOnItemClickCallback(ListKonsultasiLampauAdapter.OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListKonsultasiLampauAdapter(List<Konsultasi3> list){
        listPesanan = list;
    }

    @NonNull
    @Override
    public ListKonsultasiLampauAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_konsultasi_lampau, viewGroup, false);
        return new ListKonsultasiLampauAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListKonsultasiLampauAdapter.ListViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_namaDokter.setText( listPesanan.get(position).getNama_dokter());
        holder.tv_jadwal.setText(listPesanan.get(position).getJadwal());
        holder.tv_jam.setText(listPesanan.get(position).getJam());
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

        TextView tv_namaDokter,tv_jadwal,tv_jam;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_namaDokter  = itemView.findViewById(R.id.tv_namaDokter);
            tv_jadwal = itemView.findViewById(R.id.tv_jadwal);
            tv_jam = itemView.findViewById(R.id.tv_jam);
        }
    }

    //Onclick Interface
    public interface OnItemClickCallback {
        void onItemClicked(Konsultasi3 data);
    }
}
