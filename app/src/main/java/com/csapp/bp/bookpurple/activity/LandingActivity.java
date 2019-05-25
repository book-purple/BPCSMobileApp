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
import com.csapp.bp.bookpurple.dagger.component.ModuleComponent;
import com.csapp.bp.bookpurple.dagger.provider.ComponentProvider;
import com.csapp.bp.bookpurple.logger.Logger;
import com.csapp.bp.bookpurple.mvp.interactor.LandingPageInteractor;
import com.csapp.bp.bookpurple.mvp.interfaces.LandingViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.LandingPageRequestModel;
import com.csapp.bp.bookpurple.mvp.model.LandingPageResponseModel;
import com.csapp.bp.bookpurple.mvp.presenter.LandingPagePresenter;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;
import com.csapp.bp.bookpurple.util.rx.RxUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LandingActivity extends AppCompatActivity implements LandingViewPresenterContract.View {

    private static final String TAG = LandingActivity.class.getSimpleName();

    protected ModuleComponent component;

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
    public RxUtil rxUtil;
    @Inject
    public RxSchedulersAbstractBase rxSchedulers;
    @Inject
    public LandingPageInteractor interactor;

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
        loadData();
    }

    private void injectDependencies() {
        component = ComponentProvider.getComponent();
        component.inject(this);
        compositeDisposable = new CompositeDisposable();
    }

    private void initView() {

        createPresenter();

        offersAdapter = new OffersAdapter(this);
        offerRv = findViewById(R.id.offer_rv);
        offerRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        offerRv.setAdapter(offersAdapter);

        eventRecyclerView = findViewById(R.id.event_rv);
        eventAdapter = new GridEventAdapter(this);
        gridEventLayoutManager = new GridLayoutManager(this, 3);
        eventRecyclerView.setLayoutManager(gridEventLayoutManager);
        eventRecyclerView.setAdapter(eventAdapter);

        serviceRecyclerView = findViewById(R.id.service_rv);
        serviceAdapter = new GridServiceAdapter(this);
        gridServiceLayoutManager = new GridLayoutManager(this, 3);
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

    /**
     * Function to initialize presenter
     */
    private void createPresenter() {
        presenter = new LandingPagePresenter(this,
                interactor,
                compositeDisposable,
                rxSchedulers);
    }

    /**
     * Function to register Adapter Observables
     */
    private void registerToAdapterObservables() {

        // subscribe to event grid click
        Disposable eventClickSubscription = eventAdapter.getEventTilePublishSubject()
                .subscribe(event -> {
                    Logger.log(event.name);
                },throwable -> Logger.logException(TAG, throwable));

        compositeDisposable.add(eventClickSubscription);

        // subscribe to service grid click
        Disposable serviceClickSubscription = serviceAdapter.getServiceModelPublishSubject()
                .subscribe(service -> {
                    Logger.log(service.name);
                }, throwable -> Logger.logException(TAG, throwable));

        compositeDisposable.add(serviceClickSubscription);
    }

    private void setData() {
        // function to fill rest with dummy details
        DummyDataProvider dummyDataProvider = new DummyDataProvider();
        offersAdapter.addData(dummyDataProvider.getLandingOffersModels());
        bAdapter1.addData(dummyDataProvider.getBusinessModels1());
        bAdapter2.addData(dummyDataProvider.getBusinessModels1());
    }

    /**
     * Function to provide Data from API to activity
     * @param landingPageResponseModel landingPageResponseModel
     */
    @Override
    public void onLandingDataFetched(LandingPageResponseModel landingPageResponseModel) {
        // fill event grid details
        eventAdapter.addData(landingPageResponseModel.landingGridDto.eventGrid.eventTileList);
        // fill service grid details
        serviceAdapter.addData(landingPageResponseModel.landingGridDto.serviceGrid.serviceTileList);
    }

    @Override
    public void loadData() {
        // todo: get location from user location service common util
        presenter.getLandingData(new LandingPageRequestModel(0L, 0L));
    }

    @Override
    public void dataFetchFailure(Throwable error) {
        Logger.log(TAG, error);
    }
}
