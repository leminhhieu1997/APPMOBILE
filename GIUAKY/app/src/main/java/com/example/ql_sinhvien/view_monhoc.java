package com.example.ql_sinhvien;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ql_sinhvien.adapter.diem_adapter;
import com.example.ql_sinhvien.adapter.monhoc_adapter;
import com.example.ql_sinhvien.adapter.spinner_khoa;
import com.example.ql_sinhvien.adapter.spinner_monhoc;
import com.example.ql_sinhvien.dao.diem_dao;
import com.example.ql_sinhvien.dao.monhoc_dao;
import com.example.ql_sinhvien.model.diem_model;
import com.example.ql_sinhvien.model.monhoc_model;

import java.util.ArrayList;
import java.util.List;

public class view_monhoc extends AppCompatActivity implements RecyclerItemTouchHelperD.RecyclerItemTouchHelperListener{

    private static final String TAG = "Home_MH_view";
    private EditText edt_diem;
    private Spinner listMonHoc;
    private Button btn_ok;
    String id = "";

    monhoc_dao db ;
    diem_dao db_diem;

    private RecyclerView listDiem;
    private List<diem_model> List_Diem;
    private diem_adapter adapter;
    private CoordinatorLayout coordinatorLayout;

    private List<monhoc_model> monHoc_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_monhoc);


        db = new monhoc_dao(getApplicationContext());
        db_diem = new diem_dao(getApplicationContext());


        listDiem = findViewById(R.id.list_Diem);
        List_Diem = new ArrayList<diem_model>();

        adapter = new diem_adapter(List_Diem,getApplicationContext());
        RecyclerView.LayoutManager mlayoutM = new LinearLayoutManager(getApplicationContext());
        listDiem.setLayoutManager(mlayoutM);
        listDiem.setItemAnimator(new DefaultItemAnimator());
        listDiem.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listDiem.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadData();



        edt_diem = findViewById(R.id.edt_diem);

        listMonHoc = findViewById(R.id.listMonHoc2);
        btn_ok = findViewById(R.id.btn_ok);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        monHoc_list = new ArrayList<monhoc_model>();

        db.getAllMonHoc(monHoc_list);
        spinner_monhoc adapterMonhoc = new spinner_monhoc(getApplicationContext(), R.layout.row_item, monHoc_list);
        adapterMonhoc.setDropDownViewResource(R.layout.row_item);
        listMonHoc.setAdapter(adapterMonhoc);
        Click();
    }

    public void loadData(){
        // Log.e(TAG,"qua1");
        List_Diem.clear();
        db_diem.getAllDiem(List_Diem);
        adapter.notifyDataSetChanged();
    }

    public void Click(){
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if(edt_diem.getText().toString().equals("")){
                    // Toast.makeText(getApplicationContext(),"Could not field emty!", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"Không được để trống bất kỳ trường nào",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    diem_model diem = new diem_model();
                    diem.setMaSSV(id);

                    diem.setMaMH(monHoc_list.get(listMonHoc.getSelectedItemPosition()).getMaMH());
                    diem.setDiem(Integer.parseInt(edt_diem.getText().toString()));
                    db_diem.createDiem(diem);
                    Toast.makeText(getApplicationContext(),"create sucessful",Toast.LENGTH_SHORT).show();
                    loadData();

                }
            }
        });
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof diem_adapter.MyViewHolder) {
            // boolean flat_undo = false;
            // get the removed item name to display it in snack bar
            String name = List_Diem.get(viewHolder.getAdapterPosition()).getMaMH();

            // backup of removed item for undo purpose
            final diem_model deletedItem = List_Diem.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            db_diem.deleteDiem(deletedItem);

            // remove the item from recycler view
            adapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from Diem!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter.restoreItem(deletedItem, deletedIndex);
                    db_diem.createDiem(deletedItem);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds cartList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
