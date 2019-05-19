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
import com.csapp.bp.bookpurple.util.rx.RxViewUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class GridServiceAdapter extends RecyclerView.Adapter<GridServiceAdapter.GridListViewHolder> {

    private List<ServiceModel> serviceModels = new ArrayList<>();
    private PublishSubject<ServiceModel> serviceModelPublishSubject;

    public GridServiceAdapter(Context context) {
        this.serviceModelPublishSubject = PublishSubject.create();
    }

    public void addData(List<ServiceModel> models) {
        serviceModels.clear();
        serviceModels.addAll(models);
        notifyDataSetChanged();
    }

    public PublishSubject<ServiceModel> getServiceModelPublishSubject () {
        return serviceModelPublishSubject;
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

        private View view;
        private TextView name;
        private ImageView image;

        GridListViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.service_name);
            image = itemView.findViewById(R.id.service_image);
        }

        @Override
        public void onClick(View v) {
        }

        public void populate(ServiceModel serviceModel) {
            name.setText(serviceModel.serviceName);
            image.setImageResource(serviceModel.serviceImage);

            RxViewUtil.click(view)
                    .subscribe(aVoid -> serviceModelPublishSubject.onNext(serviceModel), throwable -> {});
        }
    }
}
