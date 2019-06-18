package com.csapp.bp.bookpurple.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.constant.Constant;
import com.csapp.bp.bookpurple.dagger.component.ModuleComponent;
import com.csapp.bp.bookpurple.dagger.provider.ComponentProvider;
import com.csapp.bp.bookpurple.logger.Logger;
import com.csapp.bp.bookpurple.mvp.interactor.VendorDetailsPageInteractor;
import com.csapp.bp.bookpurple.mvp.interfaces.VendorDetailsViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.response.VendorDetailsPageResponseModel;
import com.csapp.bp.bookpurple.mvp.presenter.VendorDetailsPagePresenter;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;
import com.csapp.bp.bookpurple.util.rx.RxUtil;
import com.csapp.bp.bookpurple.util.rx.RxViewUtil;
import com.jakewharton.rxbinding2.widget.TextViewBeforeTextChangeEvent;
import com.tuyenmonkey.mkloader.MKLoader;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

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

    /**
     * View Related Variables
     */
    private RelativeLayout contentView;
    private RelativeLayout loadingView;
    private TextView tvVendorName;
    private TextView tvVendorDesc;
    private TextView tvVendorProvidedServices;
    private TextView tvVendorTags;
    private Button btnEnquiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);
        injectDependencies();
        handleIntent();
        initView();
        onLoad();
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

        contentView = findViewById(R.id.details_page_view);
        loadingView = findViewById(R.id.details_page_loader);
        tvVendorName = findViewById(R.id.vendor_name);
        tvVendorDesc = findViewById(R.id.vendor_desc);
        tvVendorProvidedServices = findViewById(R.id.provided_services);
        tvVendorTags = findViewById(R.id.tags);
        btnEnquiry = findViewById(R.id.enquiry);

        registerToEnquiryButtonClick();
    }

    private void registerToEnquiryButtonClick() {
        Disposable enquiryButtonClickSubscription = RxViewUtil.click(btnEnquiry)
                .subscribe(aVoid -> changeButtonText(), throwable -> Logger.logException(TAG, throwable));
        lifecycle.add(enquiryButtonClickSubscription);
    }

    private void changeButtonText() {
        btnEnquiry.setText("Enquiry Placed");
    }

    @Override
    public void onVendorDetailsPageDataFetched(VendorDetailsPageResponseModel vendorDetailsPageResponseModel) {
        stopLoadingAnimation();
        updateUi(vendorDetailsPageResponseModel);
    }

    private void updateUi(VendorDetailsPageResponseModel vendorDetailsPageResponseModel) {
        tvVendorName.setText(vendorDetailsPageResponseModel.vendorDetails.vendorName);
        tvVendorDesc.setText(vendorDetailsPageResponseModel.vendorDetails.vendorDesc);
        tvVendorProvidedServices.setText(presenter.getProvidedServices(vendorDetailsPageResponseModel.vendorDetails.providedServices));
        tvVendorTags.setText(presenter.getProvidedServices(vendorDetailsPageResponseModel.vendorDetails.tags));
    }

    @Override
    public void onLoad() {
        startLoadingAnimation();
        presenter.fetchVendorDetailsPageData(vendorId);
    }

    @Override
    public void onDataFetchFailure(Throwable throwable) {
        // TODO: Handle Error Screen
    }

    private void startLoadingAnimation() {
        loadingView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    private void stopLoadingAnimation() {
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }
}
