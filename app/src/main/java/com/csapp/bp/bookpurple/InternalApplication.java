package com.csapp.bp.bookpurple;

import android.content.Context;

/*
 * Created by gauravsharma on 2019-05-19.
 */
public final class InternalApplication {

    private static final String TAG = InternalApplication.class.getName();
    private static InternalApplication internalApplication;

    private Context application;

    public static InternalApplication getApplication() {
        if (internalApplication == null) {
            throw new NullPointerException("Internal Application class not intialized");
        }
        return internalApplication;
    }

    public static InternalApplication initialize(Context application) {
        internalApplication = new InternalApplication(application);
        return internalApplication;
    }

    private InternalApplication(Context application) {
        this.application = application;
    }

    public Context getApplicationContext() {
        return application;
    }
}
