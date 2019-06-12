package com.csapp.bp.bookpurple.enums;

import android.support.annotation.IntDef;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
@IntDef({
        ListingItemViewType.VIEW_TYPE_VENDOR,
        ListingItemViewType.BANNER
})
public @interface ListingItemViewType {
    int VIEW_TYPE_VENDOR = 1;
    int BANNER = 2;
}
