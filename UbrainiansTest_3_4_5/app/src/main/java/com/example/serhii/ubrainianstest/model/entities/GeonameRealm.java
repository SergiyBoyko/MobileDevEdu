package com.example.serhii.ubrainianstest.model.entities;

import io.realm.RealmObject;

/**
 * Created by Serhii on 08.05.2018.
 */

public class GeonameRealm extends RealmObject {
    private String name;

    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
