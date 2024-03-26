package com.example.btl_qlct.Activity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.btl_qlct.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                nextActivity();
            }
        },500);
    }
    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            Intent intent= new Intent(this,DangNhapActivity.class);
            startActivity(intent);
        }else{
            Intent intent= new Intent(this, TrangChuActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
