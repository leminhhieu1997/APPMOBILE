package com.example.ql_sinhvien.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ql_sinhvien.Home;
import com.example.ql_sinhvien.Home_mh;
import com.example.ql_sinhvien.R;
import com.example.ql_sinhvien.model.sinhvien_model;
import com.example.ql_sinhvien.update_sinhvien;
import com.example.ql_sinhvien.view_monhoc;

import java.util.List;

public class sinhvien_adapter extends RecyclerView.Adapter<sinhvien_adapter.MyViewHolder> {


    private List<sinhvien_model> sinhvien_modelList;
    private Context mContext;
    private int Flat;

    public sinhvien_adapter(List<sinhvien_model> sinhvien_modelList, Context mContext, int Flat) {
        this.sinhvien_modelList = sinhvien_modelList;
        this.mContext = mContext;
        this.Flat = Flat;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {


        public TextView txt_TenSV, txt_MaSinhVien,txt_khoa;
        public RelativeLayout viewBackground,viewForeground;
        public FrameLayout row_sinhvien;
        public ImageButton update;
        public MyViewHolder(View view){
            super(view);
            txt_TenSV = view.findViewById(R.id.txt_TenSV);
            txt_MaSinhVien = view.findViewById(R.id.txt_MaSinhVien);
            txt_khoa = view.findViewById(R.id.txt_khoa);
            viewForeground = view.findViewById(R.id.view_foreground);
            viewBackground = view.findViewById(R.id.view_background);
            row_sinhvien = view.findViewById(R.id.sinhvien_row);
            update = view.findViewById(R.id.btn_update);
            if(Flat==1){
                update.setVisibility(view.INVISIBLE);
            }
        }

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sinhvien, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        sinhvien_model sv = sinhvien_modelList.get(i);
        myViewHolder.txt_TenSV.setText(sv.getHoVaTen());
        myViewHolder.txt_MaSinhVien.setText(sv.getMaSSV());
        //Log.e("sinhvien_adapter",sv.getMaKhoa()+"  error");
        myViewHolder.txt_khoa.setText(sv.getMaKhoa());
        if(Flat!=1){
            myViewHolder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent_update = new Intent(mContext, update_sinhvien.class);
                    intent_update.putExtra("id",  myViewHolder.txt_MaSinhVien.getText().toString());
                    intent_update.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    mContext.startActivity(intent_update);
                }
            });
        }else{
            myViewHolder.row_sinhvien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_MonHoc = new Intent(mContext, view_monhoc.class);
                    intent_MonHoc.putExtra("id",  myViewHolder.txt_MaSinhVien.getText().toString());
                    intent_MonHoc.putExtra("name",  myViewHolder.txt_MaSinhVien.getText().toString());
                    intent_MonHoc.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    mContext.startActivity(intent_MonHoc);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return sinhvien_modelList.size();
    }

    public void removeItem(int position) {
       sinhvien_modelList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(sinhvien_model item, int position) {
        sinhvien_modelList.add(position, item);
        notifyItemInserted(position);
    }

}
