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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class FragmentContact extends Fragment {
    View v;
    RecyclerView recyclerView;
    private List<Contact> listContact;
    RecyclerViewAdapter recyclerViewAdapter;

    public FragmentContact() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_fragment, container, false);
        recyclerView = v.findViewById(R.id.contact_recyclerview);
        String Uiid  = getArguments().getString("Uiid");
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),listContact,Uiid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        listContact = new ArrayList<>();
        listContact.add(new Contact("Nhan Hoa 1", "0779339323","https://firebasestorage.googleapis.com/v0/b/qldanhba-fbcaa.appspot.com/o/User%2FNt08Mww7JQStFkxDHpZhBlNbcrH2%2F872622151?alt=media&token=3f459370-2c99-493f-8978-68efff6c8d6f", R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 1", "0779339323","https://i.imgur.com/DvpvklR.png", R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 1", "0779339323","https://i.imgur.com/DvpvklR.png", R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 1", "0779339323","https://i.imgur.com/DvpvklR.png", R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 1", "0779339323","https://i.imgur.com/DvpvklR.png", R.drawable.ic_call));
        listContact.add(new Contact("Nhan Hoa 1", "0779339323","https://i.imgur.com/DvpvklR.png", R.drawable.ic_call));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView)searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

}
