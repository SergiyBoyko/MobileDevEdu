package com.example.serhii.ubrainianstest;

import android.support.multidex.MultiDexApplication;

import com.example.serhii.ubrainianstest.di.component.AppComponent;
import com.example.serhii.ubrainianstest.di.component.DaggerAppComponent;
import com.example.serhii.ubrainianstest.di.module.ApiModule;
import com.example.serhii.ubrainianstest.di.module.AppModule;

/**
 * Created by Serhii on 08.02.2018.
 */

public class App extends MultiDexApplication {
    private AppComponent appComponent;

    public App() {
        super();

        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }

}
