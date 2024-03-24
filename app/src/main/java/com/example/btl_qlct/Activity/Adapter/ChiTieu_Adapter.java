package com.example.btl_qlct.Activity.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_qlct.Activity.Model.ChiTieuModel;
import com.example.btl_qlct.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChiTieu_Adapter extends RecyclerView.Adapter<ChiTieu_Adapter.UserViewHodler> {

    private List<ChiTieuModel> mChiTieuModel;

    //construct có tham số
    public ChiTieu_Adapter(List<ChiTieuModel> chiTieuModel){
        this.mChiTieuModel=chiTieuModel;
    }

    @NonNull
    @Override
    public ChiTieu_Adapter.UserViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitieu,parent,false);

        return new UserViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTieu_Adapter.UserViewHodler holder, int position) {
        ChiTieuModel chiTieuModel= mChiTieuModel.get(position);
        if(chiTieuModel == null){
            return;
        }
        holder.txt_MoTa.setText(chiTieuModel.getMoTa());
        holder.txt_Ngay.setText(chiTieuModel.getNgay());
        holder.txt_Tien.setText(String.valueOf(chiTieuModel.getSoTien()));

    }

    @Override
    public int getItemCount() {
        if(mChiTieuModel!=null){
            return  mChiTieuModel.size();
        }
        return 0;
    }

    public class UserViewHodler extends RecyclerView.ViewHolder {
        private TextView txt_MoTa,txt_Ngay,txt_Tien;
        private CircleImageView civ_image;

        public UserViewHodler(@NonNull View itemView) {
            super(itemView);
            txt_MoTa=itemView.findViewById(R.id.txt_MoTa);
            txt_Ngay=itemView.findViewById(R.id.txt_Ngay);
            txt_Tien= itemView.findViewById(R.id.txt_Tien);
            civ_image=itemView.findViewById(R.id.civ_image);
        }
    }
}
