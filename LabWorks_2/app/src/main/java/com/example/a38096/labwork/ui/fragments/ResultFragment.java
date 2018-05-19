package com.example.a38096.labwork.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.a38096.labwork.R;
import com.example.a38096.labwork.utils.FontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultFragment extends Fragment {

    @BindView(R.id.text_source)
    EditText etSource;

    @BindView(R.id.text_result)
    EditText etResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.ok)
    public void OnOkClick() {
        etResult.setTypeface(FontUtils.tf);
        etResult.setText(etSource.getText());
    }

    @OnClick(R.id.cancel)
    public void OnCancelClick() {
        etSource.setText("");
        etResult.setText(getString(R.string.click_ok_for_result));
    }

}
