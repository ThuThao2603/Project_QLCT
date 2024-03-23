package com.example.btl_qlct.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.btl_qlct.R;

import java.util.Calendar;

public class ThemActivity extends AppCompatActivity {

    EditText ed_sotien, ed_mota;
    TextView txt_ngay;
    ImageButton btnlich;
    Button btn_them, btn_huy;
    RadioButton rdAn, rdMua, rdHoc, rdCo, rdGiai;
    DatePickerDialog datePickerDialog;
    int  mYear, mMonth, mDay;
    final Calendar c1 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        unitUi();

    }
    private void unitUi(){
        ed_mota = findViewById(R.id.edt_mo_ta);
        ed_sotien = findViewById(R.id.edt_tien);
        txt_ngay = findViewById(R.id.txt_them_ngay);
        btnlich = findViewById(R.id.btn_lich);
        btnlich.setOnClickListener(this::clickngay);

    }

    private void clickngay (View view) {
                //lấy ra ngày tháng năm hiện tại
                mYear = c1.get(Calendar.YEAR);
                mMonth = c1.get(Calendar.MONTH);
                mDay = c1.get(Calendar.DAY_OF_MONTH);
                //xử lý sự kiện trên datepickerDialog
                datePickerDialog = new DatePickerDialog(ThemActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                                //set hiển thị lên text view
                                txt_ngay.setText(d + "/" + (m + 1) + "/" + y);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


    }



}