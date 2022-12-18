package com.tubes.myapplication.goKlinik.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tubes.myapplication.goKlinik.Model.Jadwal;
import com.tubes.myapplication.R;
import java.util.List;

public class ListJadwalAdapter extends RecyclerView.Adapter<ListJadwalAdapter.ListViewHolder> {

    List<Jadwal> listCoffeeDrink;

    //Onclick
    private OnItemClickCallback onItemClickCallback;

    //Onclick Method
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListJadwalAdapter(List<Jadwal> list){
        listCoffeeDrink = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_jadwal, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.tvTanggal.setText(listCoffeeDrink.get(position).getJadwal());
        holder.tvJam.setText(listCoffeeDrink.get(position).getJam());
        //Onclick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listCoffeeDrink.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCoffeeDrink.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvTanggal, tvJam;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_item_tanggal);
            tvJam = itemView.findViewById(R.id.tv_item_jam);
        }
    }

    //Onclick Interface
    public interface OnItemClickCallback {
        void onItemClicked(Jadwal data);
    }
}
