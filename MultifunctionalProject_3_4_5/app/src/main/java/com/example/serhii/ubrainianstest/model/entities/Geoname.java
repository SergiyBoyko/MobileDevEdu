package com.example.serhii.ubrainianstest.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Serhii on 08.05.2018.
 */

public class Geoname {
    @SerializedName("toponymName")
    private String toponymName;

    public String getToponymName() {
        return toponymName;
    }

}
