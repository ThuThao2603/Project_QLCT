package com.example.btl_qlct.Activity.Adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_qlct.Activity.Model.ChiTieuModel;
import com.example.btl_qlct.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CTadapter extends RecyclerView.Adapter<CTadapter.myViewHolder> {

    private List<ChiTieuModel> chiTieuModels;
    private iClick mIclick;
    private List<ChiTieuModel> chiTieuModelsOld;

    //khai báo để call sự kiện ra ngoài
    public interface iClick{
        void clickSua(ChiTieuModel chiTieuModel);
        void clickXoa(ChiTieuModel chiTieuModel);
    }

    public CTadapter(List<ChiTieuModel> chiTieuModels, iClick click) {
        this.chiTieuModels = chiTieuModels;
        this.mIclick = click;
        this.chiTieuModelsOld= chiTieuModels;
    }

    Context context;
    String kc = "";
    DatePickerDialog datePickerDialog;
    int  mYear, mMonth, mDay;
    final Calendar c1 = Calendar.getInstance();

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

        //hiển thị
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

        //sửa
        holder.ibSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIclick.clickSua(chiTieuModel);
            }
        });

        //Xóa
        holder.ibXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIclick.clickXoa(chiTieuModel);
            }
        });
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
        ImageButton ibSua, ibXoa;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img);
            mota = (TextView) itemView.findViewById(R.id.txt_mota);
            ngay = (TextView) itemView.findViewById(R.id.txt_ngay);
            sotien = (TextView) itemView.findViewById(R.id.txt_tien);
            ibSua = itemView.findViewById(R.id.ibSua);
            ibXoa = itemView.findViewById(R.id.ibXoa);
        }
    }
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                System.out.println("test: " + strSearch);
                // check chưa điền kí tự hoặc xóa hết đi
                if(strSearch.isEmpty()){
                    chiTieuModels=chiTieuModelsOld;
                }else{
                    List<ChiTieuModel> list= new ArrayList<>();
                    for(ChiTieuModel model : chiTieuModelsOld){
                        // nếu tên model chứa kí tự ta search thì ta add nó vào list
                        if(model.getMota().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(model);
                        }
                    }
                    // cho về kqua
                    chiTieuModels=list;
                }
                FilterResults filterResults= new FilterResults();
                // chả về
                filterResults.values=chiTieuModels;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                chiTieuModels= (List<ChiTieuModel>) results.values;
                //nhận bt thay đổi
                notifyDataSetChanged();
            }
        };
    }
}
