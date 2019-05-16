package com.nhanhoa.quanlydanhba;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentSms extends Fragment {
    View v;
    RecyclerView recyclerView;
    private List<SMS> listSMS;

    public FragmentSms() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.sms_fragment, container, false);
        recyclerView = v.findViewById(R.id.sms_recyclerview);
        RecyclerViewAdapterForSMS recyclerViewAdapterForSMS = new RecyclerViewAdapterForSMS(getContext(),listSMS);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapterForSMS);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listSMS = new ArrayList<>();
        listSMS.add(new SMS("Nhan Hoa","Xin chao"));
        listSMS.add(new SMS("Nhan Hoa","Xin chao"));
        listSMS.add(new SMS("Nhan Hoa","Xin chao"));
    }
}
