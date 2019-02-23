package com.csapp.bp.bookpurple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.csapp.bp.bookpurple.adapter.GridEventAdapter;
import com.csapp.bp.bookpurple.adapter.GridServiceAdapter;
import com.csapp.bp.bookpurple.adapter.OffersAdapter;

public class BPMainActivity extends AppCompatActivity {

    private RecyclerView offerRv;
    private OffersAdapter offersAdapter;

    private GridEventAdapter eventAdapter;
    private GridServiceAdapter serviceAdapter;
    private GridLayoutManager gridEventLayoutManager;
    private RecyclerView eventRecyclerView;
    private GridLayoutManager gridServiceLayoutManager;
    private RecyclerView serviceRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpmain);
        initView();
        setData();
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
    }

    private void setData() {
        DummyDataProvider dummyDataProvider = new DummyDataProvider();
        offersAdapter.addData(dummyDataProvider.getLandingOffersModels());
        eventAdapter.addData(dummyDataProvider.getEventModels());
        serviceAdapter.addData(dummyDataProvider.getServiceModels());
    }
}
