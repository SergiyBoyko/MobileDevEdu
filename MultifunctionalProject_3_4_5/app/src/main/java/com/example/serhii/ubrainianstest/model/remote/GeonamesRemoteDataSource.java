package com.example.serhii.ubrainianstest.model.remote;

import com.example.serhii.ubrainianstest.api.GeonamesApi;
import com.example.serhii.ubrainianstest.model.IGeonamesDataSource;
import com.example.serhii.ubrainianstest.model.entities.GeoResponse;

import rx.Observable;

/**
 * Created by Serhii on 08.05.2018.
 */

public class GeonamesRemoteDataSource implements IGeonamesDataSource {
    private GeonamesApi geonameApi;

    public GeonamesRemoteDataSource(GeonamesApi geonameApi) {
        this.geonameApi = geonameApi;
    }

    @Override
    public Observable<GeoResponse> getSearchResult(String phrase) {
        return geonameApi.getSearchResult(phrase);
    }
}
