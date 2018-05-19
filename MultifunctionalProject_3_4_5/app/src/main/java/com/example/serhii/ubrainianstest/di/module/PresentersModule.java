package com.example.serhii.ubrainianstest.di.module;

import com.example.serhii.ubrainianstest.di.scope.Scope;
import com.example.serhii.ubrainianstest.di.scope.Scopes;
import com.example.serhii.ubrainianstest.model.IGeonamesDataSource;
import com.example.serhii.ubrainianstest.presenters.GeonamesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhii on 08.02.2018.
 */


@Module
public class PresentersModule {

    @Provides
    @Scope(Scopes.VIEW)
    public GeonamesPresenter provideGeonamesPresenter(IGeonamesDataSource geonamesDataSource) {
        return new GeonamesPresenter(geonamesDataSource);
    }

}
