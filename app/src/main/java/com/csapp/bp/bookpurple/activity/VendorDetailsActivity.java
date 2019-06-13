package com.csapp.bp.bookpurple.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.constant.Constant;
import com.csapp.bp.bookpurple.dagger.component.ModuleComponent;
import com.csapp.bp.bookpurple.dagger.provider.ComponentProvider;
import com.csapp.bp.bookpurple.mvp.interactor.VendorDetailsPageInteractor;
import com.csapp.bp.bookpurple.mvp.interfaces.VendorDetailsViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.response.VendorDetailsPageResponseModel;
import com.csapp.bp.bookpurple.mvp.presenter.VendorDetailsPagePresenter;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;
import com.csapp.bp.bookpurple.util.rx.RxUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class VendorDetailsActivity extends AppCompatActivity implements VendorDetailsViewPresenterContract.View {

    private static final String TAG = VendorDetailsActivity.class.getSimpleName();

    // Dagger related variables
    @Inject
    public RxUtil rxUtil;
    @Inject
    public RxSchedulersAbstractBase rxSchedulers;
    @Inject
    public VendorDetailsPageInteractor vendorDetailsPageInteractor;
    protected ModuleComponent component;

    // Rx Related Variables
    private CompositeDisposable lifecycle;

    private VendorDetailsPagePresenter presenter;
    private String vendorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);
        injectDependencies();
        handleIntent();
        initView();
    }

    private void injectDependencies() {
        component = ComponentProvider.getComponent();
        component.inject(this);
        lifecycle = new CompositeDisposable();

        // create presenter
        presenter = new VendorDetailsPagePresenter(this,
                lifecycle,
                vendorDetailsPageInteractor,
                rxSchedulers);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.vendorId = bundle.getString(Constant.VENDOR_ID);
    }


    private void initView() {
    }

    @Override
    public void onVendorDetailsPageDataFetched(VendorDetailsPageResponseModel vendorDetailsPageResponseModel) {

    }

    @Override
    public void onLoad() {
        presenter.fetchVendorDetailsPageData(vendorId);
    }

    @Override
    public void onDataFetchFailure(Throwable throwable) {
        // TODO: Handle Error Screen
    }
}
