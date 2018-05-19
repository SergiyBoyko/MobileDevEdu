package com.example.serhii.ubrainianstest.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Serhii on 08.05.2018.
 */

public class GeoResponse {
    @SerializedName("geonames")
    private List<Geoname> geonames = null;

    public List<Geoname> getGeonames() {
        return geonames;
    }

}
