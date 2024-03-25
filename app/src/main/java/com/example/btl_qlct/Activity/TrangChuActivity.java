package com.example.btl_qlct.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.btl_qlct.Activity.Adapter.CTadapter;
import com.example.btl_qlct.Activity.Model.ChiTieuModel;
import com.example.btl_qlct.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {

    private ImageButton ibHome,ibHanMuc,ibThongKe,ibDangXuat, ibThem, ibSua, ibXoa;
    RecyclerView recyclerView;

    CTadapter cTadapter;
    List<ChiTieuModel> chiTieuModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
       /* recyclerView = recyclerView.findViewById(R.id.ls_chitieu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<ChiTieuModel> options =
                new FirebaseRecyclerOptions.Builder<ChiTieuModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ChiTieu"), ChiTieuModel.class)
                        .build();

        chiTieuAdapter = new ChiTieuAdapter(options);
        recyclerView.setAdapter(chiTieuAdapter);*/
        // gọi hàm
        unitUi();
        unitUilistener();
        getList();



    }

    private void unitUi(){
        ibDangXuat= findViewById(R.id.ibDangXuat);
        ibHome=findViewById(R.id.ibHome);
        ibThongKe=findViewById(R.id.ibThongKe);

        ibThem = findViewById(R.id.ibInsert);
        ibSua = findViewById(R.id.ibSua);
        ibXoa = findViewById(R.id.ibXoa);


        recyclerView = findViewById(R.id.ls_chitieu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //phân cách các chi tiêu
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        chiTieuModelList = new ArrayList<>();
        cTadapter = new CTadapter(chiTieuModelList);
        recyclerView.setAdapter(cTadapter);
    }



    private void getList(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ChiTieu");
        Query query = myRef.orderByChild("id_nguoidung").equalTo(user.getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //xóa dữ liệu
                if(chiTieuModelList !=null){
                    chiTieuModelList.clear();
                }
                //chạy vòng lặp add dữ liệu vào chitieumodel
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ChiTieuModel chiTieuModel = dataSnapshot.getValue(ChiTieuModel.class);
                    chiTieuModelList.add(chiTieuModel);
                }

                cTadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TrangChuActivity.this,"Lỗi",Toast.LENGTH_SHORT).show();
            }
        });
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
        ibThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TrangChuActivity.this,ThemActivity.class);
                startActivity(intent);
            }
        });



    }
}