package com.example.btl_qlct.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_qlct.Activity.Adapter.CTadapter;
import com.example.btl_qlct.Activity.Model.ChiTieuModel;
import com.example.btl_qlct.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrangChuActivity extends AppCompatActivity {

    private ImageButton ibHome,ibHanMuc,ibThongKe,ibDangXuat, ibThem, ibSua, ibXoa;
    private  EditText edSearch;
    RecyclerView recyclerView;

    CTadapter cTadapter;

    DatePickerDialog datePickerDialog;
    int  mYear, mMonth, mDay;
    final Calendar c1 = Calendar.getInstance();
    List<ChiTieuModel> chiTieuModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        // gọi hàm
        unitUi();
        unitUilistener();
        getList();



    }

    private void unitUi(){
        ibDangXuat= findViewById(R.id.ibDangXuat);
        ibHome=findViewById(R.id.ibHome);
        ibThongKe=findViewById(R.id.ibThongKe);

        ibThem = findViewById(R.id.ibInsert);
        ibSua = findViewById(R.id.ibSua);
        ibXoa = findViewById(R.id.ibXoa);
        edSearch= findViewById(R.id.edSearch);


        recyclerView = findViewById(R.id.ls_chitieu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //phân cách các chi tiêu
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        chiTieuModelList = new ArrayList<>();
        cTadapter = new CTadapter(chiTieuModelList, new CTadapter.iClick() {
            @Override
            public void clickSua(ChiTieuModel chiTieuModel) {
                dialogSua(chiTieuModel);
            }

            @Override
            public void clickXoa(ChiTieuModel chiTieuModel) {
                dialogXoa(chiTieuModel);
            }
        });
        recyclerView.setAdapter(cTadapter);
    }



    private void getList(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ChiTieu");
        Query query = myRef.orderByChild("id_nguoidung").equalTo(user.getUid());

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChiTieuModel chiTieuModel = snapshot.getValue(ChiTieuModel.class);
                if(chiTieuModel !=null){
                    chiTieuModelList.add(chiTieuModel);
                    //cập nhật giao diện
                    cTadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChiTieuModel chiTieuModel = snapshot.getValue(ChiTieuModel.class);
                if(chiTieuModel == null || chiTieuModelList == null|| chiTieuModelList.isEmpty()){
                    return;
                }
                //chạy vòng lặp trỏ đến id dữ liệu vừa sửa và thay đổi
                for (int i = 0; i < chiTieuModelList.size(); i++ ){
                    if(chiTieuModel.getId_chitieu() == chiTieuModelList.get(i).getId_chitieu()){
                        chiTieuModelList.set(i, chiTieuModel);
                        break;
                    }
                }
                cTadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                ChiTieuModel chiTieuModel = snapshot.getValue(ChiTieuModel.class);
                if(chiTieuModel == null || chiTieuModelList == null|| chiTieuModelList.isEmpty()){
                    return;
                }
                for (int i = 0; i < chiTieuModelList.size(); i++ ){
                    if(chiTieuModel.getId_chitieu() == chiTieuModelList.get(i).getId_chitieu()){
                        chiTieuModelList.remove(chiTieuModelList.get(i));
                        break;
                    }
                }
                cTadapter.notifyDataSetChanged();


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void dialogSua(ChiTieuModel chiTieuModel){

        Dialog dialog = new Dialog(TrangChuActivity.this);
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
                datePickerDialog = new DatePickerDialog(TrangChuActivity.this,
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
                String kc = "";
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


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("ChiTieu");

                myRef.child(chiTieuModel.getId_chitieu()).updateChildren(map, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(TrangChuActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
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

    private void  dialogXoa(ChiTieuModel chiTieuModel) {
        Dialog dialog = new Dialog(TrangChuActivity.this);
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
                        Toast.makeText(TrangChuActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
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
    private void unitUilistener(){
        ibDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(TrangChuActivity.this, DangNhapActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TrangChuActivity.this,TrangChuActivity.class);
                startActivity(intent);
            }
        });
        ibThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TrangChuActivity.this,ThongKeActivity.class);
                startActivity(intent);
            }
        });
        ibThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TrangChuActivity.this,ThemActivity.class);
                startActivity(intent);
            }
        });
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            //trc khi thay đổi
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            //khi thay đổi
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //charSequence s = charSequence constraint
                cTadapter.getFilter().filter(s);
            }
            @Override
            //sau khi thay đổi
            public void afterTextChanged(Editable s) {

            }
        });


    }
}