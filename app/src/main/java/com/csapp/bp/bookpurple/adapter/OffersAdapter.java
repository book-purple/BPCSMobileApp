package com.csapp.bp.bookpurple.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.model.LandingOffersModel;

import java.util.ArrayList;
import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OfferListViewHolder> {

    private List<LandingOffersModel> landingOffers = new ArrayList<>();

    public OffersAdapter(Context context) {

    }

    public void addData(List<LandingOffersModel> landingOffersModels) {
        landingOffers.clear();
        landingOffers.addAll(landingOffersModels);
        notifyDataSetChanged();
    }


    @Override
    public OfferListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_layout_item, parent, false);
        return new OfferListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfferListViewHolder holder, int position) {
        holder.populateData(landingOffers.get(position));
    }

    @Override
    public int getItemCount() {
        return landingOffers.size();
    }


    public class OfferListViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private RelativeLayout offerBannerRl;

        OfferListViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.offer_banner_text);
            offerBannerRl = itemView.findViewById(R.id.offer_banner_rl);
        }

        void populateData(LandingOffersModel landingOffersModel) {
            text.setText(landingOffersModel.text);
            offerBannerRl.setBackgroundColor(landingOffersModel.color);
        }
    }
}
