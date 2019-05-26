package com.csapp.bp.bookpurple.dagger.component;

import com.csapp.bp.bookpurple.activity.LandingActivity;
import com.csapp.bp.bookpurple.activity.ListingActivity;

/*
 * Written by Gaurav Sharma on 2019-05-19.
 */
public interface ModuleComponent {

    void inject(LandingActivity landingActivity);
    void inject(ListingActivity listingActivity);
}
