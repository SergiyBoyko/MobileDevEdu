package com.example.serhii.ubrainianstest.widgets.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.serhii.ubrainianstest.R;
import com.example.serhii.ubrainianstest.model.entities.Geoname;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii on 08.05.2018.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<Geoname> geonames = null;
    private OnResultClickListener resultClickListener;

    public SearchResultAdapter(List<Geoname> geonames, OnResultClickListener resultClickListener) {
        this.geonames = geonames;
        this.resultClickListener = resultClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(geonames.get(position).getToponymName());
    }

    @Override
    public int getItemCount() {
        return geonames == null ? 0 : geonames.size();
    }

    public void setGeonames(List<Geoname> geonames) {
        this.geonames = geonames;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_name)
        TextView textView;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_search, parent, false));

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(view ->
                    resultClickListener.onResultClick(geonames.get(getAdapterPosition())));
        }
    }

    public interface OnResultClickListener {
        void onResultClick(Geoname geoname);
    }
}
