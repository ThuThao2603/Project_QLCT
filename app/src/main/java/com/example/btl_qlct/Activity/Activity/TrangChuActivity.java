package com.example.btl_qlct.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_qlct.Activity.Adapter.ChiTieuAdapter;
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

import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {
    private ImageButton ibHome,ibHanMuc,ibThongKe,ibDangXuat, ibThem;
    private EditText edSearch;
    RecyclerView recyclerView;
    ChiTieuAdapter CTAdapter;
    List<ChiTieuModel> chiTieuModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

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
        edSearch= findViewById(R.id.edSearch);

        recyclerView = findViewById(R.id.ls_chitieu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //phân cách các chi tiêu
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        chiTieuModelList = new ArrayList<>();
        CTAdapter = new ChiTieuAdapter(chiTieuModelList);
        recyclerView.setAdapter(CTAdapter);
    }

    private void getList(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ChiTieu");
        Query query = myRef.orderByChild("id_nguoidung").equalTo(user.getUid());

//        cách 1
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //xóa dữ liệu
//                if(chiTieuModelList !=null){
//                    chiTieuModelList.clear();
//                }
//                //chạy vòng lặp add dữ liệu vào chitieumodel
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    ChiTieuModel chiTieuModel = dataSnapshot.getValue(ChiTieuModel.class);
//                    chiTieuModelList.add(chiTieuModel);
//                }
//                cTadapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(TrangChuActivity.this,"Lỗi",Toast.LENGTH_SHORT).show();
//            }
//        });
        //cách 2
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // get 1 user
                ChiTieuModel chiTieuModel = snapshot.getValue(ChiTieuModel.class);
                //check user get != null -> add vào list
                if(chiTieuModel!=null){
//                    if(chiTieuModel.getKhoanchi().contains(khoanchi)){
                        chiTieuModelList.add(chiTieuModel);
//                    }
                    CTAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
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
                Intent intent= new Intent(TrangChuActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            //trc khi thay đổi
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            //khi thay đổi
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //charSequence s = charSequence constraint
                CTAdapter.getFilter().filter(s);
            }

            @Override
            //sau khi thay đổi
            public void afterTextChanged(Editable s) {

            }
        });
    }
}