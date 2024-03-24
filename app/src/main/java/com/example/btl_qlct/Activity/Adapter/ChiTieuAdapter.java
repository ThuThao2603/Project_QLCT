//package com.example.btl_qlct.Activity.Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.btl_qlct.Activity.Model.ChiTieuModel;
//import com.example.btl_qlct.R;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//
//public class ChiTieuAdapter extends FirebaseRecyclerAdapter<ChiTieuModel,ChiTieuAdapter.myViewHolder> {
//
//    public ChiTieuAdapter(@NonNull FirebaseRecyclerOptions<ChiTieuModel> options) {
//        super(options);
//
//    }
//
//    @Override
////    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ChiTieuModel model) {
////        ChiTieuModel ct =
////        holder.moTa.setText(model.getMoTa());
////        holder.ngay.setText(model.getNgay());
////        holder.soTien.setText(model.getSoTien());
////
////
////        if(model.getKhoanChi().equals("Ăn uống"))
////            holder.civImage.setImageResource(R.drawable.diet);
////        if(model.getKhoanchi().equals("Giải trí"))
////            holder.img.setImageResource(R.drawable.game);
////        if(model.getKhoanchi().equals("Mua sắm"))
////            holder.img.setImageResource(R.drawable.shopping);
////        if(model.getKhoanchi().equals("Học tập"))
////            holder.img.setImageResource(R.drawable.study);
////        if(model.getKhoanchi().equals("Cố định"))
////            holder.img.setImageResource(R.drawable.dollar);
////    }
////
////    @NonNull
////    @Override
////    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitieu,parent,false);
////        return new myViewHolder(view);
////    }
//

//}
