package com.example.serhii.ubrainianstest.views;

import com.example.serhii.ubrainianstest.model.entities.Geoname;

import java.util.List;

/**
 * Created by Serhii on 08.05.2018.
 */

public interface GeonamesView extends BaseView {

    void showSearchResult(List<Geoname> geonames);

}
