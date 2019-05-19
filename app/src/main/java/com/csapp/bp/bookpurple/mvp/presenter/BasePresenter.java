package com.csapp.bp.bookpurple.mvp.presenter;

import java.lang.ref.WeakReference;

/*
 * Base Class for presenter to contain functions common to all presenters
 * Created by gauravsharma on 2019-05-19.
 */
public abstract class BasePresenter<V> {

    private WeakReference<V> weakReference;

    protected void attachView(V view) {
        weakReference = new WeakReference<>(view);
    }

    protected boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    protected V getView() {
        return weakReference.get();
    }
}
