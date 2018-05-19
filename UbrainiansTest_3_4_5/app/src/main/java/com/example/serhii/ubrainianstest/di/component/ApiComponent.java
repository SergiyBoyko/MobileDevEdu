package com.example.serhii.ubrainianstest.di.component;

import com.example.serhii.ubrainianstest.model.IGeonamesDataSource;

import retrofit2.Retrofit;

/**
 * Created by Serhii on 08.02.2018.
 */

public interface ApiComponent {

    Retrofit retrofit();

    IGeonamesDataSource geonamesDataSource();
}
