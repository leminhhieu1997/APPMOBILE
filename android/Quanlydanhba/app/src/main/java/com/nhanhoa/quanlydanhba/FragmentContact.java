package com.nhanhoa.quanlydanhba;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentContact extends Fragment {
    View v;
    RecyclerView recyclerView;
    private List<Contact> listContact;

    public FragmentContact() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);
        recyclerView = v.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),listContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listContact = new ArrayList<>();
        listContact.add(new Contact("Nhan Hoa 1", "0779339323", Uri.parse("https://drive.google.com/open?id=0Bx6C3aMbJQOHYjRVRFJVdHFrQzA"), R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 2", "0779339323", Uri.parse("https://drive.google.com/open?id=0Bx6C3aMbJQOHYjRVRFJVdHFrQzA"), R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 3", "0779339323", Uri.parse("https://drive.google.com/open?id=0Bx6C3aMbJQOHYjRVRFJVdHFrQzA"), R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 4", "0779339323", Uri.parse("https://drive.google.com/open?id=0Bx6C3aMbJQOHYjRVRFJVdHFrQzA"), R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 5", "0779339323", Uri.parse("https://drive.google.com/open?id=0Bx6C3aMbJQOHYjRVRFJVdHFrQzA"), R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 6", "0779339323", Uri.parse("https://drive.google.com/open?id=0Bx6C3aMbJQOHYjRVRFJVdHFrQzA"), R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 7", "0779339323", Uri.parse("https://drive.google.com/open?id=0Bx6C3aMbJQOHYjRVRFJVdHFrQzA"), R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 8", "0779339323", Uri.parse("https://drive.google.com/open?id=0Bx6C3aMbJQOHYjRVRFJVdHFrQzA"), R.drawable.ic_call));
    }
}
