package com.nhanhoa.quanlydanhba;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;


public class FragmentCall extends Fragment {
    View v;
    RecyclerView recyclerView;
    private List<Contact> listContact;

    public FragmentCall() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.call_fragment, container, false);
        recyclerView = v.findViewById(R.id.call_recyclerview);
        String Uiid = getArguments().getString("Uiid");
       // Log.e("abcd",Uiid);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),listContact,Uiid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listContact = new ArrayList<>();
        listContact.add(new Contact("Nhan Hoa 1", "0779339323", "https://i.imgur.com/DvpvklR.png", R.drawable.ic_call_made));
        listContact.add(new Contact("Nhan Hoa 1", "0779339323", "https://i.imgur.com/DvpvklR.png", R.drawable.ic_call_made));
        listContact.add(new Contact("Nhan Hoa 1", "0779339323", "https://i.imgur.com/DvpvklR.png", R.drawable.ic_call_made));
        listContact.add(new Contact("Nhan Hoa 1", "0779339323", "https://i.imgur.com/DvpvklR.png", R.drawable.ic_call_made));
        listContact.add(new Contact("Nhan Hoa 1", "0779339323", "https://i.imgur.com/DvpvklR.png", R.drawable.ic_call_made));
       }
}
