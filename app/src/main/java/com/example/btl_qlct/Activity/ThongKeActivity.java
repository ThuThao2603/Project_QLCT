package com.example.btl_qlct.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_qlct.R;
import com.google.firebase.auth.FirebaseAuth;

public class ThongKeActivity extends AppCompatActivity {

    private ImageButton ibHome,ibHanMuc,ibThongKe,ibDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        onClickListener();
        unitUi();

    }
    private void unitUi(){
        ibDangXuat= findViewById(R.id.ibDangXuat);
        ibHome=findViewById(R.id.ibHome);
        ibThongKe=findViewById(R.id.ibThongKe);
    }
    private void onClickListener(){
        ibThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ThongKeActivity.this, TrangChuActivity.class);
                startActivity(intent);
            }
        });
        ibDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(ThongKeActivity.this, DangNhapActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ThongKeActivity.this,TrangChuActivity.class);
                startActivity(intent);
            }
        });
    }
}