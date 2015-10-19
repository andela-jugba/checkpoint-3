package com.andela.checkpoint.converter.fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andela.checkpoint.converter.R;
import com.andela.checkpoint.converter.model.Currency;
import com.andela.checkpoint.converter.utilities.recyclerView.RecyclerViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopTenFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    public TopTenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_ten, container, false);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewAdapter(getContext(), Currency.getTopTen());
        recyclerView.setAdapter(adapter);
        return v;

    }

}
