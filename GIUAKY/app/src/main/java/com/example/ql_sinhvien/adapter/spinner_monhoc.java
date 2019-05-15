package com.example.ql_sinhvien.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ql_sinhvien.R;
import com.example.ql_sinhvien.model.monhoc_model;

import java.util.List;

public class spinner_monhoc extends ArrayAdapter<monhoc_model> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<monhoc_model> lists;
    private  int resource;

    public spinner_monhoc( Context context, int resource,List<monhoc_model> data) {
        super(context, resource, 0, data);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.resource = resource;
        this.lists = data;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {

        final View view = mInflater.inflate(resource, parent, false);
        TextView tvShow = view.findViewById(R.id.show);
        monhoc_model monhoc = lists.get(position);
        tvShow.setText(monhoc.getTenMH());
        return view;
    }
}
