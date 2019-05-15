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
import com.example.ql_sinhvien.update_khoa;
import com.example.ql_sinhvien.R;
import com.example.ql_sinhvien.model.khoa_model;

import java.util.List;

public class khoa_adapter extends RecyclerView.Adapter<khoa_adapter.MyViewHolder>  {


    private List<khoa_model> khoa_modelList;
    private Context mContext;

    public khoa_adapter(List<khoa_model> khoa_modelList, Context mContext) {
        this.khoa_modelList = khoa_modelList;
        this.mContext = mContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {


        public TextView txt_TenKhoa, txt_MaKhoa;
        public RelativeLayout viewBackground,viewForeground;
        public FrameLayout  row_khoa;
        public ImageButton update;
        public MyViewHolder(View view){
            super(view);
            txt_TenKhoa = view.findViewById(R.id.txt_TenKhoa);
            txt_MaKhoa = view.findViewById(R.id.txt_MaKhoa);
            viewForeground = view.findViewById(R.id.view_foreground);
            viewBackground = view.findViewById(R.id.view_background);
            row_khoa = view.findViewById(R.id.khoa_row);
            update = view.findViewById(R.id.btn_update);
        }

    }



    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_khoa, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        khoa_model khoa = khoa_modelList.get(i);
        myViewHolder.txt_TenKhoa.setText(khoa.getTenKhoa());
        myViewHolder.txt_MaKhoa.setText(khoa.getMaKhoa());

        myViewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent_update = new Intent(mContext,update_khoa.class);
                intent_update.putExtra("id",  myViewHolder.txt_MaKhoa.getText().toString());
                intent_update.putExtra("name", myViewHolder.txt_TenKhoa.getText().toString());
              intent_update.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
              mContext.startActivity(intent_update);
            }
        });

    }

    @Override
    public int getItemCount() {
        return khoa_modelList.size();
    }

    public void removeItem(int position) {
        khoa_modelList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(khoa_model item, int position) {
        khoa_modelList.add(position, item);
        notifyItemInserted(position);
    }

}
