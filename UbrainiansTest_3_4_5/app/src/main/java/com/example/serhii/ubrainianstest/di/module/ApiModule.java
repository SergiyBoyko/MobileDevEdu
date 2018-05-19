package com.example.serhii.ubrainianstest.di.module;

import com.example.serhii.ubrainianstest.api.GeonamesApi;
import com.example.serhii.ubrainianstest.common.Constants;
import com.example.serhii.ubrainianstest.model.IGeonamesDataSource;
import com.example.serhii.ubrainianstest.model.entities.Geoname;
import com.example.serhii.ubrainianstest.model.remote.GeonamesRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Serhii on 08.02.2018.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.GEONAMES_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    IGeonamesDataSource provideGeonamesDataSource(Retrofit retrofit) {
        return new GeonamesRemoteDataSource(retrofit.create(GeonamesApi.class));
    }
}
