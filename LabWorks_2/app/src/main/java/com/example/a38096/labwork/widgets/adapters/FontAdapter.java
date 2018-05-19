package com.example.a38096.labwork.widgets.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a38096.labwork.R;
import com.example.a38096.labwork.utils.FontUtils;

import java.util.List;

import butterknife.BindView;

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.ViewHolder> {

    private List<String> mFonts;

    private OnFontClickListener clickListener;

    private Context mContext;

    public FontAdapter(List<String> mFonts, OnFontClickListener clickListener, Context mContext) {
        this.mFonts = mFonts;
        this.clickListener = clickListener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mFontName.setTypeface(FontUtils.getTypefaceByString(mFonts.get(position), mContext));
        holder.mFontName.setText(mFonts.get(position));
    }

    @Override
    public int getItemCount() {
        return mFonts == null ? 0 : mFonts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.font_name)
        protected TextView mFontName;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_font, parent, false));

            mFontName = itemView.findViewById(R.id.font_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Toast.makeText(
//                            mContext,
//                            "Font " + mFonts.get(getAdapterPosition()),
//                            Toast.LENGTH_SHORT
//                    ).show();

                    clickListener.onFontClick(
                            FontUtils.getTypefaceByString(mFonts.get(getAdapterPosition()), mContext)
                    );
                }
            });
        }
    }

    public interface OnFontClickListener {
        void onFontClick(Typeface tf);
    }
}
