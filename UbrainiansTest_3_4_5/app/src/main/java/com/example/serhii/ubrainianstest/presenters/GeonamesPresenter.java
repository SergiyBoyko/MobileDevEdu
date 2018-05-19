package com.example.serhii.ubrainianstest.presenters;

import com.example.serhii.ubrainianstest.model.IGeonamesDataSource;
import com.example.serhii.ubrainianstest.model.entities.GeoResponse;
import com.example.serhii.ubrainianstest.utils.rx.RxErrorAction;
import com.example.serhii.ubrainianstest.utils.rx.RxRetryWithDelay;
import com.example.serhii.ubrainianstest.views.GeonamesView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Serhii on 08.02.2018.
 */

public class GeonamesPresenter extends BasePresenter<GeonamesView> {
    private final IGeonamesDataSource geonamesDataSource;

    public GeonamesPresenter(IGeonamesDataSource geonamesDataSource) {
        this.geonamesDataSource = geonamesDataSource;
    }

    public void getSearchResult(String phrase) {
        addSubscription(geonamesDataSource.getSearchResult(phrase)
                .retryWhen(new RxRetryWithDelay())
                .map(GeoResponse::getGeonames)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getView()::showSearchResult, new RxErrorAction(getView().getContext()))
        );
    }
}
