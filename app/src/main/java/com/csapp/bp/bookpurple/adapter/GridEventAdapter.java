package com.csapp.bp.bookpurple.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.csapp.bp.bookpurple.DummyDataProvider;
import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.logger.Logger;
import com.csapp.bp.bookpurple.model.EventModel;
import com.csapp.bp.bookpurple.mvp.model.EventTile;
import com.csapp.bp.bookpurple.mvp.model.LandingPageRequestModel;
import com.csapp.bp.bookpurple.mvp.model.LandingPageResponseModel;
import com.csapp.bp.bookpurple.util.rx.RxViewUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class GridEventAdapter extends RecyclerView.Adapter<GridEventAdapter.GridListViewHolder> {

    public static final String TAG = GridEventAdapter.class.getName();

    private List<EventTile> eventTiles;
    private PublishSubject<EventTile> eventTilePublishSubject;
    private DummyDataProvider dummyDataProvider;

    public GridEventAdapter(Context context) {
        this.eventTiles = new ArrayList<>();
        this.eventTilePublishSubject = PublishSubject.create();
        this.dummyDataProvider = new DummyDataProvider();
    }

    public void addData(List<EventTile> eventTiles) {
        this.eventTiles.addAll(eventTiles);
        notifyDataSetChanged();
    }

    public PublishSubject<EventTile> getEventTilePublishSubject() {
        return eventTilePublishSubject;
    }

    @Override
    public GridListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bp_event_item, parent, false);
        return new GridListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridListViewHolder holder, int position) {
        holder.populate(eventTiles.get(position));
    }

    @Override
    public int getItemCount() {
        return eventTiles.size();
    }

    public class GridListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View view;
        private TextView name;
        private ImageView image;

        GridListViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.name = itemView.findViewById(R.id.event_name);
            this.image = itemView.findViewById(R.id.event_image);
        }

        @Override
        public void onClick(View v) {
        }

        public void populate(EventTile event) {
            name.setText(event.name);
            image.setImageResource(dummyDataProvider.getDummyImageResource());

            RxViewUtil.click(view)
                    .subscribe(avoid -> eventTilePublishSubject.onNext(event),
                            throwable -> {
                                Logger.logException(TAG, throwable);
                            });
        }
    }
}
