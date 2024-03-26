package com.example.btl_qlct.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_qlct.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhapActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView textDangKy;
    private EditText editEmail, editMatKhau;
    private Button buttonDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        initUi();
        unitListener();
    }

    private void unitListener() {

        textDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
        buttonDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignIn();
            }
        });
    }

    private void onClickSignIn() {

        mAuth = FirebaseAuth.getInstance();
        String strEmail= editEmail.getText().toString().trim();
        String strMatKhau= editMatKhau.getText().toString().trim();

        if(TextUtils.isEmpty(strEmail) && TextUtils.isEmpty(strMatKhau)) {

            Toast.makeText(DangNhapActivity.this,"Nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();

        }else{

            mAuth.signInWithEmailAndPassword(strEmail, strMatKhau)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                                startActivity(intent);
                            } else {

                                Toast.makeText(DangNhapActivity.this, "Thông tin không tồn tại.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void initUi(){
        textDangKy= findViewById(R.id.tvDangKy);
        editEmail=findViewById(R.id.edEmail);
        editMatKhau=findViewById(R.id.edMatKhau);
        buttonDangNhap=findViewById(R.id.btDangNhap);
    }
}
