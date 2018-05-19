package com.example.serhii.ubrainianstest.model;

import com.example.serhii.ubrainianstest.model.entities.GeoResponse;

import rx.Observable;

/**
 * Created by Serhii on 08.05.2018.
 */

public interface IGeonamesDataSource {

    Observable<GeoResponse> getSearchResult(String phrase);

}
