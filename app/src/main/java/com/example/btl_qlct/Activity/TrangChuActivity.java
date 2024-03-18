package com.example.btl_qlct.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl_qlct.R;
import com.google.firebase.auth.FirebaseAuth;

public class TrangChuActivity extends AppCompatActivity {

    private ImageButton ibHome,ibHanMuc,ibThongKe,ibDangXuat;
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
    }
}