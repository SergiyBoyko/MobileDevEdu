package com.example.serhii.ubrainianstest.di.component;

import com.example.serhii.ubrainianstest.di.module.PresentersModule;
import com.example.serhii.ubrainianstest.di.scope.Scope;
import com.example.serhii.ubrainianstest.di.scope.Scopes;
import com.example.serhii.ubrainianstest.ui.fragments.GeoNamesFragment;

import dagger.Component;

/**
 * Created by Serhii on 08.02.2018.
 */

@Scope(Scopes.VIEW)
@Component(
        modules = {PresentersModule.class},
        dependencies = {AppComponent.class}
)
public interface PresentersComponent {

    void inject(GeoNamesFragment geoNamesFragment);

}
