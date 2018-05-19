package com.example.serhii.ubrainianstest.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.serhii.ubrainianstest.R;
import com.example.serhii.ubrainianstest.utils.Decompress;
import com.example.serhii.ubrainianstest.utils.DownloadFileAsync;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii on 07.05.2018.
 */

public class ZipImageFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.download_button)
    ImageButton mDownButton;

    @BindView(R.id.url_edit_text)
    EditText mUrlEditText;

    @BindView(R.id.photo_container)
    GridView mGridView;

    private ProgressDialog mProgressDialog;

    private ImageAdapter myImageAdapter;

    public static ZipImageFragment newInstance() {
        return new ZipImageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zip_image, container, false);

        ButterKnife.bind(this, view);

        mDownButton.setOnClickListener(view1 -> {
            String url = mUrlEditText.getText().toString();
            if (!url.isEmpty() && URLUtil.isValidUrl(url) && url.endsWith(".zip"))
                downloadAndUnzipContent(url);
            else
                Toast.makeText(getContext(), R.string.no_correct_zip_url, Toast.LENGTH_SHORT).show();
        });

        myImageAdapter = new ImageAdapter(getContext());
        mGridView.setAdapter(myImageAdapter);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);

        showImages();

        return view;
    }

    private void showImages() {
        mDownButton.setClickable(true);
        mProgressDialog.hide();
        String directory = Decompress.getRootLocation(getContext());
        ArrayList<File> allFiles = new ArrayList<>();
        listf(directory, allFiles);

        myImageAdapter.add(allFiles);
//        Toast.makeText(getContext(), "Ready in " + directory, Toast.LENGTH_LONG).show();
    }

    private void downloadAndUnzipContent(String url) {
        mDownButton.setClickable(false);
        mProgressDialog.setMessage(getString(R.string.dowloading));
        mProgressDialog.show();

        DownloadFileAsync download = new DownloadFileAsync(getContext().getFilesDir() + "/content.zip", getContext(), file -> {
            Decompress unzip = new Decompress(ZipImageFragment.this.getContext(), file);
            unzip.unzip();

            showImages();
        });
        download.execute(url);
    }

    private void listf(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files);
            }
        }
    }

    public class ImageAdapter extends BaseAdapter {

        ArrayList<File> itemList = new ArrayList<File>();
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        void add(List<File> path) {
            itemList.addAll(path);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return itemList.get(arg0);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }

            Glide.with(mContext).load(itemList.get(position))

                    .centerCrop()
                    .into(imageView);

            return imageView;
        }
    }
}
