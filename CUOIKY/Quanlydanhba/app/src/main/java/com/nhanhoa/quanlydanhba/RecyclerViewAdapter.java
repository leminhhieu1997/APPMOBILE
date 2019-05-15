package com.nhanhoa.quanlydanhba;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.Button;


import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<Contact> data;
    Dialog dialog;

    public RecyclerViewAdapter(Context context, List<Contact> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);



        viewHolder.contactItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dialog.show();
                showContactDialog(viewHolder, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int position) {
        viewHolder.tvName.setText(data.get(position).getName());
        viewHolder.tvPhone.setText(data.get(position).getPhone());
        viewHolder.imgvP.setImageResource(data.get(position).getImage());
        viewHolder.sttCall.setImageResource(data.get(position).getSttCall());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout contactItem;
        private TextView tvName;
        private TextView tvPhone;
        private ImageView imgvP;
        private ImageView sttCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactItem = itemView.findViewById(R.id.contact_item);
            tvName = itemView.findViewById(R.id.name_contact);
            tvPhone = itemView.findViewById(R.id.phone_contact);
            imgvP = itemView.findViewById(R.id.img_contact);
            sttCall = itemView.findViewById(R.id.stt_call);
        }
    }

    public void showContactDialog(ViewHolder viewHolder, final int position){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.contact_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView btnSetting = dialog.findViewById(R.id.btn_setting);
        TextView dialogName = dialog.findViewById(R.id.name);
        TextView dialogPhone = dialog.findViewById(R.id.phone);
        ImageView dialogPhoto = dialog.findViewById(R.id.photo);

        dialogName.setText(data.get(viewHolder.getAdapterPosition()).getName());
        dialogPhone.setText(data.get(viewHolder.getAdapterPosition()).getPhone());
        dialogPhoto.setImageResource(data.get(viewHolder.getAdapterPosition()).getImage());

        //Toast.makeText(context, "Chọn: " + String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
        Button btnCall = dialog.findViewById(R.id.btn_call);
        Button btnSMS = dialog.findViewById(R.id.btn_sms);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCall(position);
            }
        });
        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSMS(position);
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home =  new Intent(context,SettingActivity.class);
                startActivity(context, home, Bundle.EMPTY);
            }
        });
        dialog.show();
    }

    private void intentSMS(int position) {
        Intent intent = new Intent();//keu goi lam mot hanh dong nao do, trao doi du lieu trong cung 1 ung dung hoac giua cac ung dung vs nhau
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"+data.get(position).getPhone()));
        //startActivity(intent);
        startActivity(context, intent, Bundle.EMPTY);

    }

    private void intentCall(int position) {
        Intent intent = new Intent();//keu goi lam mot hanh dong nao do, trao doi du lieu trong cung 1 ung dung hoac giua cac ung dung vs nhau
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+data.get(position).getPhone()));
        //startActivity(intent);
        startActivity(context, intent, Bundle.EMPTY);


    }
}
