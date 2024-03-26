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

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CTadapter extends RecyclerView.Adapter<CTadapter.myViewHolder> {

    private List<ChiTieuModel> chiTieuModels;
    private iClick mIclick;

    //khai báo để call sự kiện ra ngoài
    public interface iClick{
        void clickSua(ChiTieuModel chiTieuModel);
        void clickXoa(ChiTieuModel chiTieuModel);
    }

    public CTadapter(List<ChiTieuModel> chiTieuModels, iClick click) {
        this.chiTieuModels = chiTieuModels;
        this.mIclick = click;
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
        /*holder.ibSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_sua);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                dialog.setCanceledOnTouchOutside(false);

                Button btnSua = dialog.findViewById(R.id.btn_sua);
                Button btnHuy = dialog.findViewById(R.id.btn_huy);

                EditText sotien1 = dialog.findViewById(R.id.edt_tien_sua);
                EditText mota1 = dialog.findViewById(R.id.edt_mo_ta_sua);
                TextView ngay1 = dialog.findViewById(R.id.txt_them_ngay_sua);
                ImageButton btn_ngay1 = dialog.findViewById(R.id.btn_lich_sua);
                RadioButton rdAn, rdMua, rdHoc, rdCo, rdGiai;
                rdAn = dialog.findViewById(R.id.rd_an);
                rdMua = dialog.findViewById(R.id.rd_mua);
                rdHoc = dialog.findViewById(R.id.rd_hoctap);
                rdCo = dialog.findViewById(R.id.rd_codinh);
                rdGiai = dialog.findViewById(R.id.rd_giaitri);
                btn_ngay1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mYear = c1.get(Calendar.YEAR);
                        mMonth = c1.get(Calendar.MONTH);
                        mDay = c1.get(Calendar.DAY_OF_MONTH);
                        //xử lý sự kiện trên datepickerDialog
                        datePickerDialog = new DatePickerDialog(context,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int y, int mm, int dd) {
                                        //set hiển thị lên text view
                                        ngay1.setText(dd + "/" + (mm + 1) + "/" + y);
                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                });

                sotien1.setText(chiTieuModel.getSotien());
                mota1.setText(chiTieuModel.getMota());
                ngay1.setText(chiTieuModel.getNgay());
                if(chiTieuModel.getKhoanchi().equals("Ăn uống")){
                    rdAn.setChecked(true);
                }
                if(chiTieuModel.getKhoanchi().equals("Mua sắm")){
                    rdMua.setChecked(true);
                }
                if(chiTieuModel.getKhoanchi().equals("Học tập")){
                    rdHoc.setChecked(true);
                }
                if(chiTieuModel.getKhoanchi().equals("Cố định")){
                    rdCo.setChecked(true);
                }
                if(chiTieuModel.getKhoanchi().equals("Giải trí")){
                    rdGiai.setChecked(true);
                }



                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(rdAn.isChecked()){
                            kc = rdAn.getText().toString().trim();
                        }
                        if(rdCo.isChecked()){
                            kc = rdCo.getText().toString().trim();
                        }
                        if(rdGiai.isChecked()){
                            kc = rdGiai.getText().toString().trim();
                        }
                        if(rdHoc.isChecked()){
                            kc = rdHoc.getText().toString().trim();
                        }
                        if(rdMua.isChecked()){
                            kc = rdMua.getText().toString().trim();
                        }
                        Map<String, Object> map = new HashMap<>();
                        map.put("khoanchi", kc);
                        map.put("mota",mota1.getText().toString());
                        map.put("sotien",sotien1.getText().toString());
                        map.put("ngay", ngay1.getText().toString());
                        map.put("id_nguoidung", chiTieuModel.getId_nguoidung());

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("ChiTieu");

                        myRef.child(chiTieuModel.getId_chitieu()).updateChildren(map, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        //xóa
        holder.ibXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_xoa);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                dialog.setCanceledOnTouchOutside(false);

                Button btnXoa = dialog.findViewById(R.id.btn_xoa);
                Button btnHuy = dialog.findViewById(R.id.btn_huy);

                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("ChiTieu");

                        myRef.child(chiTieuModel.getId_chitieu()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });*/
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
}
