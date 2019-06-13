package com.csapp.bp.bookpurple.mvp.core;

import java.lang.ref.WeakReference;

import javax.annotation.Nullable;

/*
 * Base Class for presenter to contain functions common to all presenters
 * Written by gauravsharma on 2019-05-19.
 */
public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> weakReference;

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
    }

    /**
     * Function to check if the view is still attached to presenter.
     *
     * @return
     */
    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    /**
     * Get the attached View. Call this function to check
     * if the view is attached to avoid Null Pointer Exception.
     *
     * @return
     */
    @Nullable
    public V getView() {
        return weakReference.get();
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (null != weakReference) {
            weakReference.clear();
            weakReference = null;
        }
    }
}
