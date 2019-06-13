package com.csapp.bp.bookpurple.mvp.core.lce;

import android.support.annotation.CallSuper;

import com.csapp.bp.bookpurple.mvp.core.MvpBasePresenter;
import com.csapp.bp.bookpurple.util.rx.RxSchedulers;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

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

    /**
     * Call this function to start load.
     * cache then server + save
     */
    public final void loadData() {
        Observable<ResultWrapper> cache = loadCache();
        Observable<ResultWrapper> server = loadServerAndCache();
        log("cache: " + cache + ", server: " + server);
        if (null != cache && null != server) {
            load(Observable.concat(cache
                            .subscribeOn(rxSchedulers.getIOScheduler())
                            .observeOn(rxSchedulers.getMainThreadScheduler())
                    , server
                            .subscribeOn(rxSchedulers.getIOScheduler())
                            .observeOn(rxSchedulers.getMainThreadScheduler())));
        } else if (null != server) {
            if (isViewAttached()) {
                getView().onLoadStart();
            }
            load(server
                    .subscribeOn(rxSchedulers.getIOScheduler())
                    .observeOn(rxSchedulers.getMainThreadScheduler()));
        } else if (null != cache) {
            load(cache
                    .subscribeOn(rxSchedulers.getIOScheduler())
                    .observeOn(rxSchedulers.getMainThreadScheduler()));
        }
    }

    /**
     * Call this function on failed in loadData
     */
    public void reload() {
        if (isViewAttached()) {
            getView().onLoadStart();
        }
        if (loadFromServer() != null) {
            // delay on network error.
            load(Observable.just(1)
                    .delay(2, TimeUnit.SECONDS)
                    .flatMap(value -> loadServerAndCache())
                    .subscribeOn(rxSchedulers.getIOScheduler())
                    .observeOn(rxSchedulers.getMainThreadScheduler()));
        }
    }

    /**
     * call this method when required background data loading.
     */
    public void backgroundLoad() {
        if (loadFromServer() != null) {
            // delay on network error.
            load(loadServerAndCache()
                    .subscribeOn(rxSchedulers.getIOScheduler())
                    .observeOn(rxSchedulers.getMainThreadScheduler()));
        }
    }

    /**
     * Call this method when user explicitly say load again.
     */
    public final void retry() {
        if (isViewAttached()) {
            getView().onLoadStart();
        }
        if (loadFromServer() != null) {
            load(loadServerAndCache()
                    .subscribeOn(rxSchedulers.getIOScheduler())
                    .observeOn(rxSchedulers.getMainThreadScheduler()));
        }
    }

    public void load(Observable<ResultWrapper> observable) {
        if (null == observable) {
            return;
        }
        DisposableObserver<ResultWrapper> disposable = observable
                .subscribeWith(new DisposableObserver<ResultWrapper>() {

                    @Override
                    public void onComplete() {
                        log("Load Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        log("on load error: " + e);
                        if (e instanceof IOException) {
                            if (isViewAttached()) {
                                getView().onNetworkError(e);
                            }
                        } else {
                            if (isViewAttached()) {
                                getView().onError(e);
                            }
                        }
                    }

                    @Override
                    public void onNext(ResultWrapper resultWrapper) {
                        log("on load next: " + resultWrapper);
                        if (!isViewAttached()) {
                            return;
                        }
                        if (resultWrapper.isFromCache) {
                            if (!isNoContent(resultWrapper.value)) {
                                getView().setCachedContent(resultWrapper.value);
                            } else {
                                getView().onLoadStart();
                            }
                        } else {
                            if (isNoContent(resultWrapper.value)) {
                                inNoContent(resultWrapper.value);
                            } else {
                                getView().setContent(resultWrapper.value);
                            }
                        }
                    }

                });
        compositeDisposable.add(disposable);
    }

    /**
     * call when view state changes
     */
    public void onCacheInvalidate(T t) {
        if (null == saveToCache(t)) {
            return;
        }
        DisposableObserver<Boolean> disposableObserver = saveToCache(t)
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getIOScheduler())
                .subscribeWith(new DisposableObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        log("Invalid cache: onNext: " + aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        log("Invalid cache: onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        log("Invalid cache: onComplete");
                    }
                });

        compositeDisposable.add(disposableObserver);
    }

    private Observable<ResultWrapper> mapToResult(Observable<T> observable, final boolean isFromCache) {
        return observable.map(t -> new ResultWrapper(t, isFromCache));
    }

    private Observable<ResultWrapper> loadCache() {
        if (null == loadFromCache()) {
            return null;
        }
        return mapToResult(loadFromCache(), true);
    }

    private Observable<ResultWrapper> loadServerAndCache() {
        if (null == loadFromServer()) {
            return null;
        }
        return mapToResult(loadFromServer()
                .concatMap(t -> {
                    log("Save to cache server data.");
                    if (null != saveToCache(t)) {
                        saveToCache(t).blockingFirst();
                    }
                    if (null == loadFromCache()) {
                        log("no caching req");
                        return Observable.just(t);
                    } else {
                        log("caching #done");
                        return loadFromCache();
                    }
                }), false);
    }

    // Override by other
    protected void log(Object object) {

    }

    //Callback Functions
    public abstract Observable<T> loadFromServer();

    public abstract Observable<T> loadFromCache();

    public abstract Observable<Boolean> saveToCache(T t);

    public abstract boolean isNoContent(T t);

    protected void inNoContent(T t) {
        getView().onNoContent();
    }

    public class ResultWrapper {
        public T value;
        public boolean isFromCache;

        public ResultWrapper(T value, boolean isFromCache) {
            this.value = value;
            this.isFromCache = isFromCache;
        }

        @Override
        public String toString() {
            return isFromCache + " : " + value;
        }
    }
}
