package com.example.btl_qlct.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl_qlct.Activity.Adapter.ChiTieu_Adapter;
import com.example.btl_qlct.Activity.Model.ChiTieuModel;
import com.example.btl_qlct.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.type.DateTime;

import java.util.Date;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {
    private ImageButton ibHome,ibHanMuc,ibThongKe,ibDangXuat;
    private ImageView ivInsert;
    private EditText SoTien,MoTa,KhoanChi;
    private EditText Ngay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        // gọi hàm
        unitUi();
        unitUilistener();
    }

    private void unitUi(){
        ibDangXuat= findViewById(R.id.ibDangXuat);
        ibHome=findViewById(R.id.ibHome);
        ibThongKe=findViewById(R.id.ibThongKe);
        ivInsert= findViewById(R.id.ivInsert);
        SoTien=findViewById(R.id.edSoTien);
        MoTa=findViewById(R.id.edMoTa);
        Ngay=findViewById(R.id.edNgay);
        KhoanChi=findViewById(R.id.edKhoanChi);
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
        ivInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onClickInsertChiTieu();
            }
        });
    }

    private void onClickInsertChiTieu() {
            // hiện ở activity hiện tại
            AlertDialog.Builder builder = new AlertDialog.Builder(TrangChuActivity.this);
            // Get the layout inflater.
            LayoutInflater inflater = getLayoutInflater();
            // Inflate and set the layout for the dialog.
            // Pass null as the parent view because it's going in the dialog layout.
            builder.setView(inflater.inflate(R.layout.insert_dialog, null))
                    // Add action buttons
                    .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // Sign in the user.

                            FirebaseDatabase database =FirebaseDatabase.getInstance();
                            DatabaseReference myRef= database.getReference("InsertChiTieu");

                            int soTien= Integer.parseInt(SoTien.getText().toString().trim());
                            String moTa= MoTa.getText().toString().trim();
                            String khoanChi= KhoanChi.getText().toString().trim();
                            String ngay= Ngay.getText().toString().trim();

                            ChiTieuModel chiTieuModel = new ChiTieuModel(khoanChi,moTa,ngay,soTien);
                            ChiTieuModel model = null;
                            String User= String.valueOf(model.getSoTien());
                            myRef.child(User).setValue();

                        }
                    })
                    .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alert= builder.create();
            alert.show();
    }
}