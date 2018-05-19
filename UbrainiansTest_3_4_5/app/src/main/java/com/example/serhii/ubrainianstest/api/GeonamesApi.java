package com.example.serhii.ubrainianstest.api;

import com.example.serhii.ubrainianstest.model.entities.GeoResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Serhii on 08.05.2018.
 */

public interface GeonamesApi {
    //http://api.geonames.org/searchJSON?username=fbrswat&name_startsWith=Kyiv&maxRows=10

    @GET("/searchJSON?username=fbrswat&maxRows=10")
    Observable<GeoResponse> getSearchResult(@Query("name_startsWith") String phrase);
}
