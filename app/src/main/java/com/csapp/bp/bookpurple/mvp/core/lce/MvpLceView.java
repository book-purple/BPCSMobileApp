package com.csapp.bp.bookpurple.mvp.core.lce;

import android.support.annotation.UiThread;
import android.view.View;

import com.csapp.bp.bookpurple.mvp.core.MvpView;

/*
 * Written by Gaurav Sharma on 2019-05-24.
 */
public interface MvpLceView<T> extends MvpView {

    View getNoContentView();
    View getContentView();
    View getNetworkErrorView();
    View getErrorView();
    View getLoadingView();

    @UiThread
    void loadStart();
    @UiThread
    void onNoContent();
    @UiThread
    void setContent(T t);
    @UiThread
    void onNetworkError(Throwable throwable);
    @UiThread
    void onError(Throwable throwable);

}
