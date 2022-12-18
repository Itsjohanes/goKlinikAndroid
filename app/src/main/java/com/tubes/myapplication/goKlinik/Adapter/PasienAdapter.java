package com.tubes.myapplication.goKlinik.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tubes.myapplication.goKlinik.Activity.EditProfil;
import com.tubes.myapplication.goKlinik.Model.Pasien;
import com.tubes.myapplication.R;


import java.util.List;

public class PasienAdapter extends RecyclerView.Adapter<PasienAdapter.MyViewHolder>{
    List<Pasien> mPasienList;

    public PasienAdapter(List<Pasien> pasienList) {
        mPasienList = pasienList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_profil, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, @SuppressLint("RecyclerView") final int position){
        holder.mID.setText(mPasienList.get(position).getId());
        holder.mUsername.setText(mPasienList.get(position).getUsername());
        holder.mPassword.setText(mPasienList.get(position).getPassword());
        holder.mPassword.setText(mPasienList.get(position).getPassword());
        holder.mNama.setText(mPasienList.get(position).getNama());
        holder.mTglLahir.setText(mPasienList.get(position).getTgl_lahir());
        holder.mJenkel.setText(mPasienList.get(position).getJenkel());
        holder.mNoHP.setText(mPasienList.get(position).getNo_hp());
        holder.mAlamat.setText(mPasienList.get(position).getAlamat());
    }

    @Override
    public int getItemCount () {
        return mPasienList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mID, mUsername, mPassword, mNama, mTglLahir, mJenkel, mNoHP, mAlamat;

        public MyViewHolder(View itemView) {
            super(itemView);
            mID = (TextView) itemView.findViewById(R.id.tv_profil);
            mUsername = (TextView) itemView.findViewById(R.id.tv_username);
            mPassword = (TextView) itemView.findViewById(R.id.tv_password);
            mNama = (TextView) itemView.findViewById(R.id.tv_nama);
            mTglLahir = (TextView) itemView.findViewById(R.id.tv_tgl_lahir);
            mJenkel = (TextView) itemView.findViewById(R.id.tv_jenkel);
            mNoHP = (TextView) itemView.findViewById(R.id.tv_no_hp);
            mAlamat = (TextView) itemView.findViewById(R.id.tv_alamat);
        }
    }
}
