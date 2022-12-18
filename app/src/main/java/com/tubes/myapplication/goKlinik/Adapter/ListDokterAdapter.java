package com.tubes.myapplication.goKlinik.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tubes.myapplication.goKlinik.Config;
import com.tubes.myapplication.goKlinik.Model.Dokter;
import com.tubes.myapplication.R;
import java.util.List;

public class ListDokterAdapter extends RecyclerView.Adapter<ListDokterAdapter.ListViewHolder> {
    List<Dokter> listDokter;

    //Onclick
    private OnItemClickCallback onItemClickCallback;

    //Onclick Method
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListDokterAdapter(List<Dokter> list){
        listDokter= list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_dokter, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL + listDokter.get(position).getFoto_dokter())
                .apply(new RequestOptions().override(100,150))
                .into(holder.imgPhoto);
        holder.tvName.setText(listDokter.get(position).getNama_dokter());
        holder.tvSpesialis.setText(listDokter.get(position).getSpesialis());

        //Onclick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listDokter.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDokter.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvName, tvSpesialis;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvSpesialis = itemView.findViewById(R.id.tv_item_spesialis);
        }
    }

    //Onclick Interface
    public interface OnItemClickCallback {
        void onItemClicked(Dokter data);
    }








}
