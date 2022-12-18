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
import com.tubes.myapplication.goKlinik.Rest.ApiInterface;

import java.util.List;

public class ListKonsultasiSelesaiAdapter extends RecyclerView.Adapter<ListKonsultasiSelesaiAdapter.ListViewHolder> {
    public List<Konsultasi2> listPesanan;

    //Onclick
    private ListKonsultasiSelesaiAdapter.OnItemClickCallback onItemClickCallback;

    //Onclick Method
    public void setOnItemClickCallback(ListKonsultasiSelesaiAdapter.OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListKonsultasiSelesaiAdapter(List<Konsultasi2> list){
        listPesanan = list;
    }

    @NonNull
    @Override
    public ListKonsultasiSelesaiAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_konsultasi_selesai, viewGroup, false);
        return new ListKonsultasiSelesaiAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListKonsultasiSelesaiAdapter.ListViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_namaDokter.setText( listPesanan.get(position).getNama_dokter());
        holder.tv_jadwal.setText(listPesanan.get(position).getJadwal());
        holder.tv_antrian.setText("Antrian ke-" + listPesanan.get(position).getAntrian());
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

        TextView tv_namaDokter,tv_jadwal,tv_jam,tv_antrian;
        Button btnSelesai;
        ApiInterface mApiInterface;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_namaDokter  = itemView.findViewById(R.id.tv_namaDokter);
            tv_jadwal = itemView.findViewById(R.id.tv_jadwal);
            tv_jam = itemView.findViewById(R.id.tv_jam);
            tv_antrian = itemView.findViewById(R.id.tv_antrian);

        }
    }

    //Onclick Interface
    public interface OnItemClickCallback {
        void onItemClicked(Konsultasi2 data);
    }
}
