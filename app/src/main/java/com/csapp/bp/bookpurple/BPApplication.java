package com.csapp.bp.bookpurple;

import android.app.Application;

/*
 * Written by Gaurav Sharma on 2019-05-20.
 */
public class BPApplication extends Application {

    private InternalApplication internalApplication;
    private static BPApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        internalApplication = InternalApplication.initialize(application);
        internalApplication.initFlow();
    }
}
