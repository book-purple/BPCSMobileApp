package com.csapp.bp.bookpurple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.csapp.bp.bookpurple.adapter.GridEventAdapter;
import com.csapp.bp.bookpurple.adapter.GridServiceAdapter;

public class BPMainActivity extends AppCompatActivity {

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
        eventAdapter.addData(dummyDataProvider.getEventModels());
        serviceAdapter.addData(dummyDataProvider.getServiceModels());
    }
}
