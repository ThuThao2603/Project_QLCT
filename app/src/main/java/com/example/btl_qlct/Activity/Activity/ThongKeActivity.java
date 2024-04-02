package com.example.btl_qlct.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ThongKeActivity extends AppCompatActivity {

    private ImageButton ibHome,ibHanMuc,ibThongKe,ibDangXuat;
    private EditText ed_tk_thang,ed_tk_nam;
    private TextView txt_tong;
    private Button btThongKe;
    RecyclerView recyclerView;

    private List<ChiTieuModel> mChiTieus;
    private CTadapter cTadapter;
    private float tongTien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        // gọi hàm
        unitUi();
        unitUilistener();
    }
    private void unitUi(){
        ibDangXuat= findViewById(R.id.ibDangXuat);
        ibHome=findViewById(R.id.ibHome);
        ibThongKe=findViewById(R.id.ibThongKe);
        ed_tk_thang=findViewById(R.id.ed_tk_thang);
        ed_tk_nam=findViewById(R.id.ed_tk_nam);
        btThongKe=findViewById(R.id.bt_ThongKe);
        txt_tong= findViewById(R.id.txt_tong);

        recyclerView= findViewById(R.id.ls_thong_ke);

        //khởi tạo

        mChiTieus=new ArrayList<>();
        tongTien=0;

        cTadapter = new CTadapter(mChiTieus, new CTadapter.iClick() {
            @Override
            public void clickSua(ChiTieuModel chiTieuModel) {
            }
            @Override
            public void clickXoa(ChiTieuModel chiTieuModel) {
            }
        });
        recyclerView.setAdapter(cTadapter);
        LinearLayoutManager linearManager = new LinearLayoutManager(ThongKeActivity.this);
        recyclerView.setLayoutManager(linearManager);
    }

    private void unitUilistener(){
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
                Intent intent= new Intent(ThongKeActivity.this, TrangChuActivity.class);
                startActivity(intent);
            }
        });
        ibThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ThongKeActivity.this,ThongKeActivity.class);
                startActivity(intent);
            }
        });
        btThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChiTieus.clear();
                tongTien=0;
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("ChiTieu");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                        for(DataSnapshot data : snapshot.getChildren()) {
                            ChiTieuModel chiTieu = data.getValue(ChiTieuModel.class);
                            if (chiTieu != null) {
                                Date ngay = new SimpleDateFormat("dd/MM/yyyy").parse(chiTieu.getNgay());
                                int thang= ngay.getMonth()+1;
                                int nam= ngay.getYear()+1900;
                                if (thang == Integer.parseInt(ed_tk_thang.getText().toString()) && nam == Integer.parseInt(ed_tk_nam.getText().toString())) {
                                    mChiTieus.add(chiTieu);
                                    tongTien += Float.parseFloat(chiTieu.getSotien().toString());
                                }
                            }
                        }

                        txt_tong.setText(String.valueOf(tongTien));
                        cTadapter.notifyDataSetChanged();

                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}