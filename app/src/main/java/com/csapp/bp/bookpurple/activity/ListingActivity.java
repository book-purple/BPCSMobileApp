package com.csapp.bp.bookpurple.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.constant.Constant;
import com.csapp.bp.bookpurple.dagger.component.ModuleComponent;
import com.csapp.bp.bookpurple.dagger.provider.ComponentProvider;
import com.csapp.bp.bookpurple.mvp.interactor.ListingInteractor;
import com.csapp.bp.bookpurple.mvp.interfaces.ListingViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.request.ListingRequestModel;
import com.csapp.bp.bookpurple.mvp.model.response.LandingPageResponseModel;
import com.csapp.bp.bookpurple.mvp.presenter.ListingPresenter;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;
import com.csapp.bp.bookpurple.util.rx.RxUtil;

import org.parceler.Parcels;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ListingActivity extends AppCompatActivity implements ListingViewPresenterContract.View {

    // Dagger related variables
    protected ModuleComponent component;
    @Inject
    public RxUtil rxUtil;
    @Inject
    public RxSchedulersAbstractBase rxSchedulers;
    @Inject
    public ListingInteractor interactor;

    // Rx Related Variables
    private CompositeDisposable compositeDisposable;

    // MVP Related variables
    private ListingPresenter presenter;

    private ListingRequestModel listingRequestModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        injectDependencies();
        handleIntent();
        initView();
        loadData();
    }

    private void injectDependencies() {
        component = ComponentProvider.getComponent();
        component.inject(this);
        compositeDisposable = new CompositeDisposable();

        // create presenter
        presenter = new ListingPresenter(this,
                interactor,
                rxSchedulers,
                compositeDisposable);
    }

    private void handleIntent() {
        if (null != getIntent() && null != getIntent().getExtras()) {
            Bundle bundle = getIntent().getExtras();
            if (!bundle.containsKey(Constant.ParcelConstant.LISTING_REQUEST_MODEL)) {
                finish();
            } else {
                listingRequestModel = interactor
                        .createListingRequest(Parcels
                                .unwrap(bundle
                                        .getParcelable(Constant.ParcelConstant.LISTING_REQUEST_MODEL)));
            }
        }
    }

    private void initView() {

    }

    @Override
    public void onListingDataFetched(LandingPageResponseModel landingPageResponseModel) {

    }

    @Override
    public void loadData() {
        presenter.getListingData(listingRequestModel);
    }

    @Override
    public void dataFetchFailure(Throwable error) {

    }
}
