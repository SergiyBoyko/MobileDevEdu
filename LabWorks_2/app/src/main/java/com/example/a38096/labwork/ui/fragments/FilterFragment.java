package com.example.a38096.labwork.ui.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a38096.labwork.R;
import com.example.a38096.labwork.utils.FontUtils;
import com.example.a38096.labwork.widgets.adapters.FontAdapter;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment implements FontAdapter.OnFontClickListener {

    private RecyclerView recyclerView;
    private FontAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_filter, container, false);

        adapter = new FontAdapter(
                getTestFonts(),
                this,
                getActivity()
        );

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    private List<String> getTestFonts() {
        List<String> fonts = new ArrayList<>();
        fonts.add("Avenir");
        fonts.add("Cinzel Regular");
        fonts.add("Keepcalm medium");
        fonts.add("Platino linotype");
        fonts.add("PZ Raleigh");
        fonts.add("Rounded elegance");
        return fonts;
    }

    @Override
    public void onFontClick(Typeface tf) {
        FontUtils.tf = tf;
    }
}
