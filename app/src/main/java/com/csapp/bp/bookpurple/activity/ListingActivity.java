package com.csapp.bp.bookpurple.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.adapter.ListingAdapter;
import com.csapp.bp.bookpurple.constant.Constant;
import com.csapp.bp.bookpurple.dagger.component.ModuleComponent;
import com.csapp.bp.bookpurple.dagger.provider.ComponentProvider;
import com.csapp.bp.bookpurple.logger.Logger;
import com.csapp.bp.bookpurple.mvp.interactor.ListingInteractor;
import com.csapp.bp.bookpurple.mvp.interfaces.ListingViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.request.ListingRequestModel;
import com.csapp.bp.bookpurple.mvp.model.response.ListingResponseModel;
import com.csapp.bp.bookpurple.mvp.model.response.listing.VendorListingData;
import com.csapp.bp.bookpurple.mvp.presenter.ListingPresenter;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;
import com.csapp.bp.bookpurple.util.rx.RxUtil;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.parceler.Parcels;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Written by Gaurav Sharma on 12th June 2019
 */
public class ListingActivity extends AppCompatActivity implements ListingViewPresenterContract.View {

    private static final String TAG = ListingActivity.class.getSimpleName();
    @Inject
    public RxUtil rxUtil;
    @Inject
    public RxSchedulersAbstractBase rxSchedulers;
    @Inject
    public ListingInteractor interactor;
    // Dagger related variables
    protected ModuleComponent component;
    // Rx Related Variables
    private CompositeDisposable lifecycle;

    // MVP Related variables
    private ListingPresenter presenter;

    private ListingRequestModel listingRequestModel;
    private RecyclerView listingRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ListingAdapter adapter;

    private RelativeLayout contentLayout;
    private ShimmerFrameLayout listingShimmerLayout;

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
        lifecycle = new CompositeDisposable();

        // create presenter
        presenter = new ListingPresenter(this,
                interactor,
                rxSchedulers,
                lifecycle);
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
                                                .getParcelable(Constant.ParcelConstant.LISTING_REQUEST_MODEL)),
                                bundle.getString(Constant.ParcelConstant.LISTING_REQUEST_TYPE));
            }
        }
    }

    private void initView() {
        listingRecyclerView = findViewById(R.id.listing);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        listingRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ListingAdapter(this, lifecycle);
        listingRecyclerView.setAdapter(adapter);

        contentLayout = findViewById(R.id.listing_content_layout);
        listingShimmerLayout = findViewById(R.id.listing_shimmer_layout);

        registerToAdapterObservables();
    }

    private void registerToAdapterObservables() {

        // subscribe to listing item click
        Disposable listingItemClickSubscription = adapter.getVendorClickedItemPublishSubject()
                .subscribe(vendorClickedItem -> startVendorDetailsActivity(vendorClickedItem.vendorListingData), throwable -> Logger.logException(TAG, throwable));

        lifecycle.add(listingItemClickSubscription);
    }

    private void startVendorDetailsActivity(VendorListingData vendorListingData) {
        Intent intent = new Intent(getApplicationContext(), VendorDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.VENDOR_ID, vendorListingData.id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onListingDataFetched(ListingResponseModel listingResponseModel) {
        stopShimmerAnimation();
        if (null != listingResponseModel) {
            adapter.setData(listingResponseModel.listingItems);
        }
    }

    @Override
    public void loadData() {
        startShimmerAnimation();
        presenter.getListingData(listingRequestModel);
    }

    @Override
    public void dataFetchFailure(Throwable error) {
        Logger.log(TAG, error);
    }

    private void startShimmerAnimation() {
        contentLayout.setVisibility(View.GONE);
        listingShimmerLayout.startShimmer();
    }

    private void stopShimmerAnimation() {
        contentLayout.setVisibility(View.VISIBLE);
        listingShimmerLayout.setVisibility(View.GONE);
        listingShimmerLayout.stopShimmer();
    }
}
