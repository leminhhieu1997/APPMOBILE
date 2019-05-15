package com.example.ql_sinhvien;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_sinhvien.adapter.khoa_adapter;
import com.example.ql_sinhvien.dao.khoa_dao;
import com.example.ql_sinhvien.model.khoa_model;

import java.util.ArrayList;
import java.util.List;
import android.support.design.widget.Snackbar;

public class Home extends AppCompatActivity implements RecyclerItemTouchHelperK.RecyclerItemTouchHelperListener {
    private static final String TAG = "Home";
    khoa_dao db ;
    private EditText edt_MaKhoa, edt_TenKhoa;
    private Button btn_add;
    private RecyclerView listKhoa;
    private List<khoa_model> List_Khoa;
    private khoa_adapter adapter;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_MaKhoa = findViewById(R.id.edit_MaKhoa);
        edt_TenKhoa = findViewById(R.id.edit_TenKhoa);
        btn_add = findViewById(R.id.btn_add);
        listKhoa = findViewById(R.id.list_Khoa);
        List_Khoa = new ArrayList<khoa_model>();

        adapter = new khoa_adapter(List_Khoa,getApplicationContext());
        RecyclerView.LayoutManager mlayoutM = new LinearLayoutManager(getApplicationContext());
        listKhoa.setLayoutManager(mlayoutM);
        listKhoa.setItemAnimator(new DefaultItemAnimator());
        listKhoa.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listKhoa.setAdapter(adapter);
        db = new khoa_dao(getApplicationContext());
        loadData();
        AddData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = findViewById(R.id.coordinator_layout);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelperK(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(listKhoa);

    }

    public void AddData(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_MaKhoa.getText().toString().equals("") || edt_TenKhoa.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Could not field emty!", Toast.LENGTH_LONG).show();
                    return;
                }
                khoa_model k = new khoa_model();
                k.setTenKhoa(edt_TenKhoa.getText().toString());
                k.setMaKhoa(edt_MaKhoa.getText().toString());

                db.createKhoa(k);
                Toast.makeText(getApplicationContext(),"create sucessful", Toast.LENGTH_LONG).show();
                loadData();
            }
        });
    }
    public void loadData(){
       // Log.e(TAG,"qua1");
        List_Khoa.clear();
         db.getAllKhoa(List_Khoa);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof khoa_adapter.MyViewHolder) {
            // boolean flat_undo = false;
            // get the removed item name to display it in snack bar
            String name = List_Khoa.get(viewHolder.getAdapterPosition()).getTenKhoa();

            // backup of removed item for undo purpose
            final khoa_model deletedItem = List_Khoa.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            db.deleteKhoa(deletedItem);

            // remove the item from recycler view
            adapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from khoa!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter.restoreItem(deletedItem, deletedIndex);
                    db.createKhoa(deletedItem);
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
