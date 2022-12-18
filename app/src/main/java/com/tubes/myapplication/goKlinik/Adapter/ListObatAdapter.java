package com.tubes.myapplication.goKlinik.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tubes.myapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tubes.myapplication.goKlinik.Config;
import com.tubes.myapplication.goKlinik.Model.Obat;

import java.util.List;

public class ListObatAdapter extends RecyclerView.Adapter<ListObatAdapter.ListViewHolder> {
    public List<Obat> listObat;
    public static  String namaBeli = " ";
    public static int total = 0;

    //Onclick
    private OnItemClickCallback onItemClickCallback;

    //Onclick Method
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListObatAdapter(List<Obat> list){
        listObat= list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_obat, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL_OBAT + listObat.get(position).getGambar_obat())
                .apply(new RequestOptions().override(100,150))
                .into(holder.imgPhoto);
        holder.tvName.setText(listObat.get(position).getNama_obat());
        holder.tvHarga.setText(listObat.get(position).getHarga_obat());
        holder.tvKeterangan.setText(listObat.get(position).getKeterangan());
        //mendapatkan suatu harga dari barang
        int harga = Integer.parseInt(listObat.get(position).getHarga_obat());
        //Increment decrement
        holder.btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int jumlah = Integer.parseInt(holder.tvNumber.getText().toString());
                int stok = Integer.parseInt(listObat.get(position).getStok_obat());
                if(jumlah < stok){
                    jumlah++;
                }
                holder.tvNumber.setText(String.valueOf(jumlah));
            }
        });

        holder.btnkurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int jumlah = Integer.parseInt(holder.tvNumber.getText().toString());
                if(jumlah > 0){
                    jumlah--;
                }
                holder.tvNumber.setText(String.valueOf(jumlah));
            }
        });
        holder.btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tvNumber.getText().toString();

                int jumlah = Integer.parseInt(holder.tvNumber.getText().toString());
                int hargaSebenarnya = Integer.parseInt(holder.tvHarga.getText().toString());
                if(jumlah > 0){
                    namaBeli = namaBeli + listObat.get(position).getNama_obat() + "(" + holder.tvNumber.getText().toString() + ")" + ",";
                    total = total + (hargaSebenarnya * jumlah);
                }
                Toast.makeText(holder.itemView.getContext(), "Berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
            }
        });

        //Onclick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listObat.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listObat.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvName;
        TextView tvHarga;
        TextView tvKeterangan;
        TextView tvNumber;
        Button btntambah, btnkurang,btnKeranjang;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvHarga = itemView.findViewById(R.id.tv_item_harga);
            tvKeterangan = itemView.findViewById(R.id.tv_item_keterangan);
            tvNumber = itemView.findViewById(R.id.number);
            btntambah = itemView.findViewById(R.id.increment);
            btnkurang = itemView.findViewById(R.id.decrement);
            btnKeranjang = itemView.findViewById(R.id.tmbhkeranjang);

        }
    }

    //Onclick Interface
    public interface OnItemClickCallback {
        void onItemClicked(Obat data);
    }








}

