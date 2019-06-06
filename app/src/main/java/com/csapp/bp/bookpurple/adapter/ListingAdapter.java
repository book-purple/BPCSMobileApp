package com.csapp.bp.bookpurple.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.adapter.viewholder.ListingVendorItemViewHolder;
import com.csapp.bp.bookpurple.adapter.viewholder.ListingViewHolder;
import com.csapp.bp.bookpurple.enums.ListingItemViewType;
import com.csapp.bp.bookpurple.mvp.model.response.ListingResponseModel;
import com.csapp.bp.bookpurple.mvp.model.response.listing.ListingItemData;
import com.csapp.bp.bookpurple.publishsubject.VendorClickedItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
public class ListingAdapter extends RecyclerView.Adapter<ListingViewHolder> {

    private Context context;
    private CompositeDisposable compositeDisposable;
    private ListingResponseModel listingResponseModel;
    private List<ListingItemData> listingItemList;
    private LayoutInflater layoutInflater;
    private PublishSubject<VendorClickedItem> vendorClickedItemPublishSubject;

    public ListingAdapter(Context context, CompositeDisposable lifecycle) {
        this.context = context;
        this.compositeDisposable = lifecycle;
        this.listingItemList = new ArrayList<>();
        this.layoutInflater = LayoutInflater.from(context);
        this.vendorClickedItemPublishSubject = PublishSubject.create();
    }

    public void setData(List<ListingItemData> listingItemList) {
        this.listingItemList.clear();
        this.listingItemList.addAll(listingItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = layoutInflater.inflate(R.layout.listing_vendor_item, parent, false);
        return new ListingVendorItemViewHolder(context, itemView, vendorClickedItemPublishSubject);
        // switch case in case there are multiple views in adapter
        /*switch (viewType) {
            case ListingItemViewType.VIEW_TYPE_VENDOR:


        }*/
    }

    @Override
    public void onBindViewHolder(@NonNull ListingViewHolder holder, int position) {
        holder.bindData(listingItemList.get(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        if (ListingItemViewType.VIEW_TYPE_VENDOR == listingItemList.get(position).viewType) {
            return ListingItemViewType.VIEW_TYPE_VENDOR;
        } else {
            return ListingItemViewType.BANNER;
        }
    }

    @Override
    public int getItemCount() {
        return listingItemList.size();
    }
}
