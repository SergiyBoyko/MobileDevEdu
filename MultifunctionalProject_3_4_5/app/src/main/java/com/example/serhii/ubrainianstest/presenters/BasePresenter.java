package com.example.serhii.ubrainianstest.presenters;

import com.example.serhii.ubrainianstest.views.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Serhii on 08.02.2018.
 */

public class BasePresenter<T extends BaseView> {

    private T view;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void destroy() {
        compositeSubscription.clear();
    }

    protected Subscription addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
        return subscription;
    }

}