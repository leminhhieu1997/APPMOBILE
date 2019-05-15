package com.example.ql_sinhvien;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_sinhvien.adapter.monhoc_adapter;
import com.example.ql_sinhvien.dao.monhoc_dao;
import com.example.ql_sinhvien.model.monhoc_model;

import java.util.ArrayList;
import java.util.List;

public class Home_mh extends AppCompatActivity implements RecyclerItemTouchHelperM.RecyclerItemTouchHelperListener {

    private static final String TAG = "Home_MH";
    monhoc_dao db ;
    private EditText edt_MaMH, edt_TenMH, edt_soTiet;
    private Button btn_add;
    private RecyclerView listMonHoc;
    private List<monhoc_model> List_MonHoc;
    private monhoc_adapter adapter;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mh);
        edt_MaMH = findViewById(R.id.edit_MaMon);
        edt_TenMH = findViewById(R.id.edit_TenMon);
        edt_soTiet = findViewById(R.id.edit_soTiet2);
        btn_add = findViewById(R.id.btn_add);
        listMonHoc = findViewById(R.id.list_MonHoc);
        List_MonHoc = new ArrayList<monhoc_model>();

        adapter = new monhoc_adapter(List_MonHoc,getApplicationContext(),0,"");
        RecyclerView.LayoutManager mlayoutM = new LinearLayoutManager(getApplicationContext());
        listMonHoc.setLayoutManager(mlayoutM);
        listMonHoc.setItemAnimator(new DefaultItemAnimator());
        listMonHoc.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listMonHoc.setAdapter(adapter);
        db = new monhoc_dao(getApplicationContext());
        loadData();
        AddData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = findViewById(R.id.coordinator_layout);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelperM(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(listMonHoc);

    }

    public void AddData(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_MaMH.getText().toString().equals("") || edt_TenMH.getText().toString().equals("")|| edt_soTiet.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Could not field emty!", Toast.LENGTH_LONG).show();
                    return;
                }
                monhoc_model mh = new monhoc_model();
                mh.setTenMH(edt_TenMH.getText().toString());
                mh.setMaMH(edt_MaMH.getText().toString());
                Log.e(TAG,edt_soTiet.getText().toString()+"'");
                mh.setSoTiet(Integer.parseInt(edt_soTiet.getText().toString()));
                db.createMonHoc(mh);
                Toast.makeText(getApplicationContext(),"create sucessful", Toast.LENGTH_LONG).show();
                loadData();
            }
        });
    }
    public void loadData(){
        // Log.e(TAG,"qua1");
        List_MonHoc.clear();
        db.getAllMonHoc(List_MonHoc);
//        db.getAllMonHoc(listMonHoc);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof monhoc_adapter.MyViewHolder) {
            // boolean flat_undo = false;
            // get the removed item name to display it in snack bar
            String name = List_MonHoc.get(viewHolder.getAdapterPosition()).getTenMH();

            // backup of removed item for undo purpose
            final monhoc_model deletedItem = List_MonHoc.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            db.deleteMonHoc(deletedItem);

            // remove the item from recycler view
            adapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from Mon hoc!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter.restoreItem(deletedItem, deletedIndex);
                    db.createMonHoc(deletedItem);
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
