package com.example.serhii.ubrainianstest.widgets.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.serhii.ubrainianstest.R;
import com.example.serhii.ubrainianstest.model.entities.Geoname;
import com.example.serhii.ubrainianstest.model.entities.GeonameRealm;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Serhii on 08.05.2018.
 */

public class SavedResultAdapter extends RecyclerView.Adapter<SavedResultAdapter.ViewHolder> implements RealmChangeListener {

    private final RealmResults<GeonameRealm> geonameRealms;
    private OnHistoryLongClickListener longClickListener;
    private Context context;

    public SavedResultAdapter(Context context, RealmResults<GeonameRealm> geonameRealms, OnHistoryLongClickListener longClickListener) {
        this.context = context;
        this.geonameRealms = geonameRealms;
        this.longClickListener = longClickListener;
        geonameRealms.addChangeListener(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(geonameRealms.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return geonameRealms.size();
    }

    @Override
    public void onChange() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.saved_name)
        TextView textView;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_saved, parent, false));

            ButterKnife.bind(this, itemView);

            itemView.setOnLongClickListener(view -> {
                longClickListener.onHistoryLongClick(geonameRealms.get(getAdapterPosition()));
                return false;
            });
        }
    }

    public interface OnHistoryLongClickListener {
        void onHistoryLongClick(GeonameRealm element);
    }
}
