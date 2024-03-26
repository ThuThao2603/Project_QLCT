package com.example.btl_qlct.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_qlct.Activity.Model.ChiTieuModel;
import com.example.btl_qlct.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class InsertActivity extends AppCompatActivity {
    EditText ed_sotien, ed_mota;
    TextView txt_ngay;
    String kc = "";
    ImageButton btnlich;
    Button btn_them,btn_huy;
    RadioButton rdAn, rdMua, rdHoc, rdCo, rdGiai;
    DatePickerDialog datePickerDialog;
    int  mYear, mMonth, mDay;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        unitUi();
        getTextrd();
        addChiTieu();
    }
    private void unitUi(){
        ed_mota = findViewById(R.id.edt_mo_ta);
        ed_sotien = findViewById(R.id.edt_tien);
        txt_ngay = findViewById(R.id.txt_them_ngay);
        btnlich = findViewById(R.id.btn_lich);
        btnlich.setOnClickListener(this::clickngay);
        rdAn = findViewById(R.id.rd_an);
        rdCo = findViewById(R.id.rd_codinh);
        rdGiai = findViewById(R.id.rd_giaitri);
        rdMua = findViewById(R.id.rd_mua);
        rdHoc = findViewById(R.id.rd_hoctap);
        btn_them = findViewById(R.id.btn_them);
        btn_huy=findViewById(R.id.btn_huy);
    }
    private void getTextrd(){

        if(rdAn.isChecked()){
            kc = rdAn.getText().toString().trim();
        }
        else if(rdCo.isChecked()){
            kc = rdCo.getText().toString().trim();
        }
        else if(rdGiai.isChecked()){
            kc = rdGiai.getText().toString().trim();
        }
        else if(rdHoc.isChecked()){
            kc = rdHoc.getText().toString().trim();
        }
        else if(rdMua.isChecked()){
            kc = rdMua.getText().toString().trim();
        }
    }

    private void clickngay (View view) {
                //lấy ra ngày tháng năm hiện tại
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                //xử lý sự kiện trên datepickerDialog
                datePickerDialog = new DatePickerDialog(InsertActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int y, int mm, int dd) {
                                //set hiển thị lên text view
                                txt_ngay.setText(dd + "/" + (mm + 1) + "/" + y);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
    }

    private void addChiTieu(){
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextrd();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                String sotien = ed_sotien.getText().toString().trim();
                String mota = ed_mota.getText().toString().trim();
                String ngay = txt_ngay.getText().toString().trim();
                String khoanchi = kc.toString().trim();
                String id_nguoidung = user.getUid();

                if(sotien.equals("")||mota.equals("")||ngay.equals("")||khoanchi.equals("")){
                    Toast.makeText(InsertActivity.this,"Điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }else {
                    ChiTieuModel chiTieuModel = new ChiTieuModel(khoanchi, mota, ngay, id_nguoidung, sotien);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("ChiTieu");
                    int id = 1;

                    String path = String.valueOf("Chi Tiêu" + id );

                        myRef.push().setValue(chiTieuModel, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Intent intent = new Intent(InsertActivity.this, TrangChuActivity.class);
                                startActivity(intent);

                                Toast.makeText(InsertActivity.this,"Thêm thành công!",Toast.LENGTH_LONG).show();
                            }
                        });
                }
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InsertActivity.this, TrangChuActivity.class);
                startActivity(intent);
            }
        });
    }





}