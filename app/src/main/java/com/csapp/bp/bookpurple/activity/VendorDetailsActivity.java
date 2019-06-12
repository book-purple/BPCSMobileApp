package com.csapp.bp.bookpurple.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.dagger.component.ModuleComponent;
import com.csapp.bp.bookpurple.dagger.provider.ComponentProvider;
import com.csapp.bp.bookpurple.mvp.interfaces.VendorDetailsViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.presenter.ListingPresenter;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;
import com.csapp.bp.bookpurple.util.rx.RxUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class VendorDetailsActivity extends AppCompatActivity implements VendorDetailsViewPresenterContract.View {

    private static final String TAG = VendorDetailsActivity.class.getSimpleName();

    // Rx Related Variables
    private CompositeDisposable lifecycle;

    // Dagger related variables
    protected ModuleComponent component;
    @Inject
    public RxUtil rxUtil;
    @Inject
    public RxSchedulersAbstractBase rxSchedulers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);
        injectDependencies();
        initView();
    }

    private void injectDependencies() {
        component = ComponentProvider.getComponent();
        component.inject(this);
        lifecycle = new CompositeDisposable();
    }

    private void initView() {
    }
}
