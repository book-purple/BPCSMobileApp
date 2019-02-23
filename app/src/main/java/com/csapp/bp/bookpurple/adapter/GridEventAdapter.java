package com.csapp.bp.bookpurple.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.model.EventModel;

import java.util.ArrayList;
import java.util.List;

public class GridEventAdapter extends RecyclerView.Adapter<GridEventAdapter.GridListViewHolder> {

    private List<EventModel> eventModels = new ArrayList<>();

    public GridEventAdapter(Context context) {

    }

    public void addData(List<EventModel> models) {
        eventModels.clear();
        eventModels.addAll(models);
        notifyDataSetChanged();
    }

    @Override
    public GridListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bp_event_item, parent, false);
        return new GridListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridListViewHolder holder, int position) {
        holder.populate(eventModels.get(position));
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    public class GridListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private ImageView image;

        GridListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.event_name);
            image = itemView.findViewById(R.id.event_image);
        }

        @Override
        public void onClick(View v) {

        }

        public void populate(EventModel eventModel) {
            name.setText(eventModel.eventName);
            image.setImageResource(eventModel.eventImage);
        }
    }
}
