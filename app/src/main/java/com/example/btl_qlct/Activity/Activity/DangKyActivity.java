package com.example.btl_qlct.Activity.Activity;

import android.app.ProgressDialog;
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

public class DangKyActivity extends AppCompatActivity {

    // khai báo 1
    private TextView textDangNhap;
    private EditText editEmail,editMatKhau;
    private Button buttonDangKy;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        unitUi();
        unitListener();
    }

    private void unitListener() {

        textDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });

        buttonDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickDangKy();
            }
        });
    }

    private void setOnClickDangKy() {
//678
        String strEmail=editEmail.getText().toString().trim();
        String strMatKhau= editMatKhau.getText().toString().trim();

        FirebaseAuth auth= FirebaseAuth.getInstance();

        if(TextUtils.isEmpty(strEmail) && TextUtils.isEmpty(strMatKhau)) {

            Toast.makeText(DangKyActivity.this,"Nhập đầy đủ thông tin!",Toast.LENGTH_SHORT).show();

        }else {

            progressDialog.show();
            auth.createUserWithEmailAndPassword(strEmail, strMatKhau)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        // check đăng ký thành công hay thất bại
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(DangKyActivity.this, TrangChuActivity.class);
                                startActivity(intent);

                                finishAffinity();
                            } else {
                                Toast.makeText(DangKyActivity.this, "Kiểm tra lại thông tin đăng ký.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    //khai báo ánh xạ
    private void unitUi(){
        // ánh xạ
        textDangNhap= findViewById(R.id.tvDangNhap);
        editEmail=findViewById(R.id.edEmail);
        editMatKhau=findViewById(R.id.edMatKhau);
        buttonDangKy=findViewById(R.id.btDangKy);
        progressDialog=new ProgressDialog(this);
    }

}

