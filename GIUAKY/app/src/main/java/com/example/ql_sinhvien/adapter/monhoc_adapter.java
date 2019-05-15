package com.example.ql_sinhvien.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ql_sinhvien.R;
import com.example.ql_sinhvien.model.monhoc_model;
import com.example.ql_sinhvien.update_monhoc;

import java.util.List;

public class monhoc_adapter extends RecyclerView.Adapter<monhoc_adapter.MyViewHolder> {
    private List<monhoc_model> monhoc_modelList;
    private Context mContext;
    private int flat;
    private String idsv;

    public monhoc_adapter(List<monhoc_model> khoa_modelList, Context mContext, int flat,String idsv) {
        this.monhoc_modelList = khoa_modelList;
        this.mContext = mContext;
        this.flat =  flat;
        this.idsv = idsv;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {


        public TextView txt_TenMon, txt_MaMon, txt_soTiet;
        public RelativeLayout viewBackground,viewForeground;
        public FrameLayout row_monhoc;
        public ImageButton update;
        public MyViewHolder(View view){
            super(view);
            txt_TenMon = view.findViewById(R.id.txt_TenMon);
            txt_MaMon = view.findViewById(R.id.txt_MaMon);
            txt_soTiet = view.findViewById(R.id.txt_soTiet);
            viewForeground = view.findViewById(R.id.view_foreground);
            viewBackground = view.findViewById(R.id.view_background);
            row_monhoc = view.findViewById(R.id.row_monhoc);
            update = view.findViewById(R.id.btn_update);
            if(flat == 1){
                update.setVisibility(view.INVISIBLE);
            }
        }

    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_monhoc, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        monhoc_model monhoc = monhoc_modelList.get(i);
        myViewHolder.txt_TenMon.setText(monhoc.getTenMH());
        myViewHolder.txt_MaMon.setText(monhoc.getMaMH());
       myViewHolder.txt_soTiet.setText(String.valueOf(monhoc.getSoTiet()));
        if(flat!=1){
            myViewHolder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent_update = new Intent(mContext, update_monhoc.class);
                    intent_update.putExtra("id",  myViewHolder.txt_TenMon.getText().toString());
                    intent_update.putExtra("name", myViewHolder.txt_TenMon.getText().toString());
                    intent_update.putExtra("soTiet", myViewHolder.txt_soTiet.getText().toString());
                    intent_update.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    mContext.startActivity(intent_update);
                }
            });
        }else{
            myViewHolder.row_monhoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_diem = new Intent(mContext, update_monhoc.class);
                    intent_diem.putExtra("id",  myViewHolder.txt_TenMon.getText().toString());
                    intent_diem.putExtra("idsv",  myViewHolder.txt_TenMon.getText().toString());
                    intent_diem.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    mContext.startActivity(intent_diem);

                }
            });

        }


    }
    public int getItemCount() {
        return monhoc_modelList.size();
    }

    public void removeItem(int position) {
        monhoc_modelList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(monhoc_model item, int position) {
        monhoc_modelList.add(position, item);
        notifyItemInserted(position);
    }
}
