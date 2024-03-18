package com.example.btl_qlct.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_qlct.R;

public class ThongKeActivity extends AppCompatActivity {

    ImageButton btn_chitieu, btn_hanmuc, btn_baocao, btn_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr_thong_ke);

        btn_chitieu =findViewById(R.id.btn_chi_tieu);
        btn_baocao =findViewById(R.id.btn_bao_cao);
        btn_hanmuc = findViewById(R.id.btn_han_muc);
        btn_out = findViewById(R.id.btn_out);

        btn_chitieu.setOnClickListener(this::clickChitieu);
    }

    private void clickChitieu(View view) {
        Intent intent = new Intent(ThongKeActivity.this, TrangChuActivity.class);
        startActivity(intent);
    }
}