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

import com.example.ql_sinhvien.R;
import com.example.ql_sinhvien.model.diem_model;
import com.example.ql_sinhvien.model.khoa_model;
import com.example.ql_sinhvien.update_diem;
import com.example.ql_sinhvien.update_khoa;

import java.util.List;

public class diem_adapter extends RecyclerView.Adapter<diem_adapter.MyViewHolder> {
    private List<diem_model> diem_modelList;
    private Context mContext;
    public String Mssv;

    public diem_adapter(List<diem_model> diem_modelList, Context mContext) {
        this.diem_modelList = diem_modelList;
        this.mContext = mContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {


        public TextView txt_TenMH, txt_Diem;
        public RelativeLayout viewBackground,viewForeground;
        public FrameLayout row_khoa;
        public ImageButton update;
        public MyViewHolder(View view){
            super(view);
            txt_TenMH = view.findViewById(R.id.txt_TenKhoa);
            txt_Diem = view.findViewById(R.id.txt_MaKhoa);
            viewForeground = view.findViewById(R.id.view_foreground);
            viewBackground = view.findViewById(R.id.view_background);
            row_khoa = view.findViewById(R.id.khoa_row);
            update = view.findViewById(R.id.btn_update);
        }

    }

    @Override
    public diem_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_khoa, parent, false);

        return new diem_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final diem_adapter.MyViewHolder myViewHolder, int i) {
        diem_model diem = diem_modelList.get(i);
        myViewHolder.txt_TenMH.setText(diem.getMaMH());
        myViewHolder.txt_Diem.setText(String.valueOf(diem.getDiem()));
        Mssv = diem.getMaSSV();
        myViewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_update = new Intent(mContext, update_diem.class);
                intent_update.putExtra("id",  myViewHolder.txt_TenMH.getText().toString());
                intent_update.putExtra("diem", myViewHolder.txt_Diem.getText().toString());
                intent_update.putExtra("mssv", Mssv);
                intent_update.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                mContext.startActivity(intent_update);
            }
        });

    }

    @Override
    public int getItemCount() {
        return diem_modelList.size();
    }

    public void removeItem(int position) {
        diem_modelList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(diem_model item, int position) {
        diem_modelList.add(position, item);
        notifyItemInserted(position);
    }


}
