package com.csapp.bp.bookpurple.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.enums.ListingItemViewType;
import com.csapp.bp.bookpurple.logger.Logger;
import com.csapp.bp.bookpurple.mvp.model.response.listing.VendorListingData;
import com.csapp.bp.bookpurple.publishsubject.VendorClickedItem;
import com.csapp.bp.bookpurple.util.rx.RxViewUtil;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
public class ListingVendorItemViewHolder extends ListingViewHolder<VendorListingData> {

    private final static String TAG = ListingVendorItemViewHolder.class.getSimpleName();

    private Context context;
    private View view;
    private PublishSubject<VendorClickedItem> vendorClickedItemPublishSubject;

    /*Views*/
    private TextView vendorName;
    private TextView providedServices;
    private TextView vendorRating;
    private ImageView vendorImage;

    public ListingVendorItemViewHolder(Context context, View itemView, PublishSubject<VendorClickedItem> vendorClickedItemPublishSubject) {
        super(itemView);
        this.view = itemView;
        this.context = context;
        this.vendorClickedItemPublishSubject = vendorClickedItemPublishSubject;
        initViews(itemView);
    }

    private void initViews(View itemView) {

        vendorName = itemView.findViewById(R.id.name);
        providedServices = itemView.findViewById(R.id.provided_services);
        vendorRating = itemView.findViewById(R.id.rating);
    }

    @Override
    public void bindData(VendorListingData item, int position) {
        vendorName.setText(item.name);
        providedServices.setText(getProvidedServices(item.providedServices));
        vendorRating.setText(String.valueOf(item.rating));

        RxViewUtil.click(view)
                .subscribe(aVoid -> {
                    final VendorClickedItem vendorClickedItem = new VendorClickedItem();
                    vendorClickedItem.position = position;
                    vendorClickedItem.vendorListingData = item;
                    vendorClickedItem.viewType = ListingItemViewType.VIEW_TYPE_VENDOR;
                    vendorClickedItemPublishSubject.onNext(vendorClickedItem);
                }, throwable -> Logger.logException(TAG, throwable));
    }

    private String getProvidedServices(List<String> services) {
        return services.get(0) + ", " + services.get(1) + " and more";
    }
}
