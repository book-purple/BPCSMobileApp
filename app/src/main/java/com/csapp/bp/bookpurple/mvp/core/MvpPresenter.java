package com.csapp.bp.bookpurple.mvp.core;

/*
 * Written by Gaurav Sharma on 2019-05-24.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);
    void detachView(boolean retainInstance);
}
