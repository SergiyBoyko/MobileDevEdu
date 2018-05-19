package com.example.serhii.ubrainianstest.utils.rx;

import android.content.Context;
import android.widget.Toast;

import com.example.serhii.ubrainianstest.R;

import rx.functions.Action1;

/**
 * Created by Serhii on 08.05.2018.
 */

public class RxErrorAction implements Action1<Throwable> {

    private final Context context;

    public RxErrorAction(Context context) {
        this.context = context;
    }

    @Override
    public void call(Throwable throwable) {
        throwable.printStackTrace();

        Toast.makeText(context, context.getString(R.string.request_execution_has_failed),
                Toast.LENGTH_SHORT).show();
    }

}
