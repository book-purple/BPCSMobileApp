package com.csapp.bp.bookpurple.mvp.core;

import java.lang.ref.WeakReference;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
public abstract class MvpBasePresenterV2<V> {
    private WeakReference<V> weakRef;

    protected void attachView(V view) {
        weakRef = new WeakReference<>(view);
    }

    protected boolean isViewAttached() {
        return weakRef != null && weakRef.get() != null;
    }

    protected V getView() {
        return weakRef.get();
    }
}
