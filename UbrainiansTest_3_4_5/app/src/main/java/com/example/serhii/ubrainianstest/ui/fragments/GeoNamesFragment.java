package com.example.serhii.ubrainianstest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serhii.ubrainianstest.App;
import com.example.serhii.ubrainianstest.R;
import com.example.serhii.ubrainianstest.di.component.AppComponent;
import com.example.serhii.ubrainianstest.di.component.DaggerPresentersComponent;
import com.example.serhii.ubrainianstest.di.module.PresentersModule;
import com.example.serhii.ubrainianstest.model.entities.Geoname;
import com.example.serhii.ubrainianstest.model.entities.GeonameRealm;
import com.example.serhii.ubrainianstest.presenters.GeonamesPresenter;
import com.example.serhii.ubrainianstest.views.GeonamesView;
import com.example.serhii.ubrainianstest.widgets.adapters.SavedResultAdapter;
import com.example.serhii.ubrainianstest.widgets.adapters.SearchResultAdapter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Serhii on 07.05.2018.
 */

public class GeoNamesFragment extends android.support.v4.app.Fragment implements GeonamesView,
        SearchResultAdapter.OnResultClickListener, SavedResultAdapter.OnHistoryLongClickListener {

    @BindView(R.id.card_view)
    CardView mSearchContainer;

    @BindView(R.id.geo_search_list)
    RecyclerView mSearchRecyclerView;

    @BindView(R.id.geo_saved_list)
    RecyclerView mSavedRecyclerView;

    @BindView(R.id.geo_edit_text)
    EditText mSearchEditText;

    @Inject
    GeonamesPresenter mPresenter;

    private SearchResultAdapter mAdapter;

    private Realm mRealm;

    public static GeoNamesFragment newInstance() {
        return new GeoNamesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_geo_names, container, false);

        ButterKnife.bind(this, view);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);
        mPresenter.setView(this);

        mRealm = Realm.getInstance(getContext());

        mAdapter = new SearchResultAdapter(null, this);
        mSearchRecyclerView.setAdapter(mAdapter);
        mSearchRecyclerView.setHasFixedSize(true);
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mSavedRecyclerView.setLayoutManager(mLayoutManager);
        mSavedRecyclerView.setAdapter(new SavedResultAdapter(getContext(),
                mRealm.allObjects(GeonameRealm.class), this));

        initListeners();

        return view;
    }

    @Override
    public void showSearchResult(List<Geoname> geonames) {
        mAdapter.setGeonames(geonames);
    }

    @Override
    public void onResultClick(Geoname geoname) {
        mSearchContainer.setVisibility(View.GONE);
        mSearchEditText.setText("");

        if (geoname != null) {
            mRealm.beginTransaction();
            GeonameRealm element = mRealm.createObject(GeonameRealm.class);
            element.setName(geoname.getToponymName());
            element.setId(new Date().getTime());
            mRealm.commitTransaction();
        } else showText(getString(R.string.no_results));
    }

    @Override
    public void onHistoryLongClick(GeonameRealm element) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_this_saved_element)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    mRealm.beginTransaction();
                    RealmResults<GeonameRealm> elements = mRealm.where(GeonameRealm.class)
                            .equalTo(getString(R.string.realm_id), element.getId())
                            .equalTo(getString(R.string.realm_name), element.getName())
                            .findAll();
                    while (!elements.isEmpty()) {
                        elements.get(elements.size() - 1).removeFromRealm();
                    }
                    mRealm.commitTransaction();
                })
                .setNegativeButton(R.string.no, (dialog, id) -> {
                });
        // Create the AlertDialog object and return it
        builder.show();
    }

    private void initListeners() {
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) mSearchContainer.setVisibility(View.VISIBLE);
                else mSearchContainer.setVisibility(View.GONE);

                mPresenter.getSearchResult(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    public AppComponent getAppComponent() {
        return getApp().appComponent();
    }

    private App getApp() {
        return (App) getActivity().getApplication();
    }

    private void showText(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
