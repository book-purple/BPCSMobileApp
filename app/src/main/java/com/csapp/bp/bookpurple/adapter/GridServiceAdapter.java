package com.csapp.bp.bookpurple.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.model.ServiceModel;

import java.util.ArrayList;
import java.util.List;

public class GridServiceAdapter extends RecyclerView.Adapter<GridServiceAdapter.GridListViewHolder> {

    public GridServiceAdapter(Context context) {

    }

    private List<ServiceModel> serviceModels = new ArrayList<>();

    public void addData(List<ServiceModel> models) {
        serviceModels.clear();
        serviceModels.addAll(models);
        notifyDataSetChanged();
    }

    @Override
    public GridServiceAdapter.GridListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bp_service_item, parent, false);
        return new GridServiceAdapter.GridListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridServiceAdapter.GridListViewHolder holder, int position) {
        holder.populate(serviceModels.get(position));
    }

    @Override
    public int getItemCount() {
        return serviceModels.size();
    }

    public class GridListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private ImageView image;

        GridListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.service_name);
            image = itemView.findViewById(R.id.service_image);
        }

        @Override
        public void onClick(View v) {

        }

        public void populate(ServiceModel serviceModel) {
            name.setText(serviceModel.serviceName);
            image.setImageResource(serviceModel.serviceImage);
        }
    }
}
