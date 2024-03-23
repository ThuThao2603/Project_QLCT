package com.example.btl_qlct.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_qlct.Activity.Model.ChiTieuModel;
import com.example.btl_qlct.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CTadapter extends RecyclerView.Adapter<CTadapter.myViewHolder> {

    private List<ChiTieuModel> chiTieuModels;

    public CTadapter(List<ChiTieuModel> chiTieuModels) {
        this.chiTieuModels = chiTieuModels;
    }
    Context context;

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitieu,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        ChiTieuModel chiTieuModel = chiTieuModels.get(position);
        if(chiTieuModel == null){
            return;
        }
        holder.mota.setText(chiTieuModel.getMota());
        holder.ngay.setText(chiTieuModel.getNgay());
        holder.sotien.setText(chiTieuModel.getSotien());


        if(chiTieuModel.getKhoanchi().trim().equals("Ăn uống")){
            Glide.with(context)
                    .load(R.drawable.diet)
                    .into(holder.img);
        }
        if(chiTieuModel.getKhoanchi().trim().equals("Giải trí")){
            Glide.with(context)
                    .load(R.drawable.game)
                    .into(holder.img);
        }
        if(chiTieuModel.getKhoanchi().trim().equals("Mua sắm")){
            Glide.with(context)
                    .load(R.drawable.shopping)
                    .into(holder.img);
        }
        if(chiTieuModel.getKhoanchi().trim().equals("Học tập")){
            Glide.with(context)
                    .load(R.drawable.study)
                    .into(holder.img);
        }
        if(chiTieuModel.getKhoanchi().trim().equals("Cố định")){
            Glide.with(context)
                    .load(R.drawable.dollar)
                    .into(holder.img);
        }
    }

    @Override
    public int getItemCount() {
        if(chiTieuModels !=null){
            return chiTieuModels.size();
        }
        return 0;
    }

    class  myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView mota, ngay, sotien;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img);
            mota = (TextView) itemView.findViewById(R.id.txt_mota);
            ngay = (TextView) itemView.findViewById(R.id.txt_ngay);
            sotien = (TextView) itemView.findViewById(R.id.txt_tien);
        }
    }
}
