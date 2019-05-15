package com.example.ql_sinhvien;

import android.content.Intent;
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
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.ql_sinhvien.adapter.sinhvien_adapter;
import com.example.ql_sinhvien.dao.sinhvien_dao;
import com.example.ql_sinhvien.model.sinhvien_model;

import java.util.ArrayList;
import java.util.List;

public class Home_sv extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private static final String TAG = "Home sv";
    sinhvien_dao db ;
    private Button btn_add;
    private RecyclerView listSinhVien;
    private List<sinhvien_model> List_Sinhvien;
    private sinhvien_adapter adapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sv);
        btn_add = findViewById(R.id.btn_add);
        listSinhVien = findViewById(R.id.list_sinhvien);
        List_Sinhvien = new ArrayList<sinhvien_model>();

        adapter = new sinhvien_adapter(List_Sinhvien,getApplicationContext(),0);
        RecyclerView.LayoutManager mlayoutM = new LinearLayoutManager(getApplicationContext());
        listSinhVien.setLayoutManager(mlayoutM);
        listSinhVien.setItemAnimator(new DefaultItemAnimator());
        listSinhVien.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listSinhVien.setAdapter(adapter);
        db = new sinhvien_dao(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadData();
        AddData();


        coordinatorLayout = findViewById(R.id.coordinator_layout);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(listSinhVien);

    }


    public void AddData(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_add = new Intent(getApplicationContext(),add_sinhvien.class);
                //intent_update.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                getApplicationContext().startActivity(intent_add);
            }
        });
    }
    public void loadData(){
        // Log.e(TAG,"qua1");
        List_Sinhvien.clear();
        db.getAllSinhVien(List_Sinhvien);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof sinhvien_adapter.MyViewHolder) {
            // boolean flat_undo = false;
            // get the removed item name to display it in snack bar
            String name = List_Sinhvien.get(viewHolder.getAdapterPosition()).getHoVaTen();

            // backup of removed item for undo purpose
            final sinhvien_model deletedItem = List_Sinhvien.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            db.deleteSinhVien(deletedItem);

            // remove the item from recycler view
            adapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from sinh vien!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter.restoreItem(deletedItem, deletedIndex);
                    db.createSinhVien(deletedItem);
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
