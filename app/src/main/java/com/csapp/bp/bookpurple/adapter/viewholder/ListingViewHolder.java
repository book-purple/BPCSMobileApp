package com.csapp.bp.bookpurple.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.csapp.bp.bookpurple.mvp.model.response.listing.ListingItemData;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
public abstract class ListingViewHolder<T extends ListingItemData> extends RecyclerView.ViewHolder {

    public ListingViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(T item, int position);
}
