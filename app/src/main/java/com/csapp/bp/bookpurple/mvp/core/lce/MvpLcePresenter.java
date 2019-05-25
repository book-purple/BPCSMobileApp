package com.csapp.bp.bookpurple.mvp.core.lce;

import android.support.annotation.CallSuper;

import com.csapp.bp.bookpurple.mvp.core.MvpBasePresenter;
import com.csapp.bp.bookpurple.util.rx.RxSchedulers;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/*
 * Core Logic for MVP Presenter
 * Written by Gaurav Sharma on 2019-05-24.
 */
public abstract class MvpLcePresenter<V extends MvpLceView<T>, T> extends MvpBasePresenter<V> {

    private static final String TAG = MvpLcePresenter.class.getSimpleName();

    protected CompositeDisposable compositeDisposable;
    protected RxSchedulersAbstractBase rxSchedulers;

    public MvpLcePresenter(RxSchedulersAbstractBase rxSchedulers) {
        if (null == rxSchedulers) {
            this.rxSchedulers = new RxSchedulers();
        }
        this.rxSchedulers = rxSchedulers;
    }

    public MvpLcePresenter() {
        if (null == rxSchedulers) {
            this.rxSchedulers = new RxSchedulers();
        }
    }

    @CallSuper
    @Override
    public void attachView(V view) {
        super.attachView(view);
        this.compositeDisposable = new CompositeDisposable();
    }

    public void onResume() {
    }

    public void onPause() {
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        compositeDisposable.clear();
    }

    public final void loadData() {
        Observable<ResultWrapper> server = loadServer();
        if (null == server) {

        }
    }

    public Observable<ResultWrapper> loadServer() {
        if (null == loadFromServer()) {
            return null;
        }
        return mapToResult(loadFromServer());
    }

    private Observable<ResultWrapper> mapToResult(Observable<T> observable) {
        return observable.map(ResultWrapper::new);
    }

    public void load(Observable<ResultWrapper> observable) {
        if (null == observable) {
            return;
        }
    }

    public class ResultWrapper {
        public T value;

        public ResultWrapper(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    //Callback Functions
    public abstract Observable<T> loadFromServer();
    public abstract Observable<T> loadFromCache();
    public abstract Observable<Boolean> saveToCache(T t);

    public abstract boolean isNoContent(T t);
}
