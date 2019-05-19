package com.csapp.bp.bookpurple.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.csapp.bp.bookpurple.DummyDataProvider;
import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.adapter.BAdapter1;
import com.csapp.bp.bookpurple.adapter.BAdapter2;
import com.csapp.bp.bookpurple.adapter.GridEventAdapter;
import com.csapp.bp.bookpurple.adapter.GridServiceAdapter;
import com.csapp.bp.bookpurple.adapter.OffersAdapter;
import com.csapp.bp.bookpurple.logger.Logger;
import com.csapp.bp.bookpurple.mvp.interfaces.LandingViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.LandingPageResponseModel;
import com.csapp.bp.bookpurple.mvp.presenter.LandingPagePresenter;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;
import com.csapp.bp.bookpurple.util.rx.RxUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LandingActivity extends AppCompatActivity implements LandingViewPresenterContract.View {

    private static final String TAG = LandingActivity.class.getSimpleName();

    private RecyclerView offerRv;
    private OffersAdapter offersAdapter;

    private GridEventAdapter eventAdapter;
    private GridServiceAdapter serviceAdapter;
    private GridLayoutManager gridEventLayoutManager;
    private RecyclerView eventRecyclerView;
    private GridLayoutManager gridServiceLayoutManager;
    private RecyclerView serviceRecyclerView;

    /*Business Banners*/
    private RecyclerView brv1;
    private BAdapter1 bAdapter1;

    private RecyclerView brv2;
    private BAdapter2 bAdapter2;

    // Dagger related variables
    @Inject
    private RxUtil rxUtil;

    @Inject
    private RxSchedulersAbstractBase rxSchedulers;

    // MVP Related Variables
    private LandingPagePresenter presenter;

    // Rx Related Variables
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setContentView(R.layout.activity_bpmain);
        initView();
        setData();
    }

    private void injectDependencies() {
        compositeDisposable = new CompositeDisposable();
    }

    private void initView() {

        offersAdapter = new OffersAdapter(this);
        offerRv = findViewById(R.id.offer_rv);
        offerRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        offerRv.setAdapter(offersAdapter);

        eventRecyclerView = findViewById(R.id.event_rv);
        eventAdapter = new GridEventAdapter(this);
        gridEventLayoutManager = new GridLayoutManager(this, 4);
        eventRecyclerView.setLayoutManager(gridEventLayoutManager);
        eventRecyclerView.setAdapter(eventAdapter);

        serviceRecyclerView = findViewById(R.id.service_rv);
        serviceAdapter = new GridServiceAdapter(this);
        gridServiceLayoutManager = new GridLayoutManager(this, 4);
        serviceRecyclerView.setLayoutManager(gridServiceLayoutManager);
        serviceRecyclerView.setAdapter(serviceAdapter);

        bAdapter1 = new BAdapter1(this);
        brv1 = findViewById(R.id.bp_brv_1);
        brv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        brv1.setAdapter(bAdapter1);

        bAdapter2 = new BAdapter2(this);
        brv2 = findViewById(R.id.bp_brv_2);
        brv2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        brv2.setAdapter(bAdapter2);

        registerToAdapterObservables();
    }

    private void registerToAdapterObservables() {
        Disposable eventClickSubscription = serviceAdapter.getServiceModelPublishSubject()
                .subscribe(serviceModel -> {
                    // start listing page from here
                }, throwable -> Logger.log(TAG, throwable));

        compositeDisposable.add(eventClickSubscription);
    }

    private void setData() {
        DummyDataProvider dummyDataProvider = new DummyDataProvider();
        offersAdapter.addData(dummyDataProvider.getLandingOffersModels());
        eventAdapter.addData(dummyDataProvider.getEventModels());
        serviceAdapter.addData(dummyDataProvider.getServiceModels());
        bAdapter1.addData(dummyDataProvider.getBusinessModels1());
        bAdapter2.addData(dummyDataProvider.getBusinessModels1());
    }

    @Override
    public void onLandingDataFetched(LandingPageResponseModel landingPageResponseModel) {

    }
}
