package com.example.a38096.labquick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_text)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.radio_1)
    public void onClick1() {
        editText.setTypeface(FontUtils.getTypefaceByString(getString(R.string.avenir), this));
    }

    @OnClick(R.id.radio_2)
    public void onClick2() {
        editText.setTypeface(FontUtils.getTypefaceByString(getString(R.string.cizel), this));
    }

    @OnClick(R.id.radio_3)
    public void onClick3() {
        editText.setTypeface(FontUtils.getTypefaceByString(getString(R.string.keepkalm), this));
    }

    @OnClick(R.id.radio_4)
    public void onClick4() {
        editText.setTypeface(FontUtils.getTypefaceByString(getString(R.string.platino), this));
    }

    @OnClick(R.id.radio_5)
    public void onClick5() {
        editText.setTypeface(FontUtils.getTypefaceByString(getString(R.string.pz), this));
    }

    @OnClick(R.id.radio_6)
    public void onClick6() {
        editText.setTypeface(FontUtils.getTypefaceByString(getString(R.string.rounded), this));
    }

    @OnClick(R.id.ok)
    public void onClickOk() {
        Toast.makeText(this, getResources().getString(R.string.ok), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cancel)
    public void onClickCancel() {
        editText.setText("");
    }
}
