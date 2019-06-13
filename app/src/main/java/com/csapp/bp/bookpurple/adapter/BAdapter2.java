package com.csapp.bp.bookpurple.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csapp.bp.bookpurple.R;
import com.csapp.bp.bookpurple.model.BusinessModel;

import java.util.ArrayList;
import java.util.List;

public class BAdapter2 extends RecyclerView.Adapter<BAdapter2.BViewHolder2> {

    private List<BusinessModel> businessModels = new ArrayList<>();

    public BAdapter2(Context context) {

    }

    public void addData(List<BusinessModel> businessModelList) {
        businessModels.clear();
        businessModels.addAll(businessModelList);
        notifyDataSetChanged();
    }

    @Override
    public BViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.b_layout2, parent, false);
        return new BAdapter2.BViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(BViewHolder2 holder, int position) {
        holder.populate(businessModels.get(position));
    }

    @Override
    public int getItemCount() {
        return businessModels.size();
    }

    public class BViewHolder2 extends RecyclerView.ViewHolder {

        private TextView text;
        private RelativeLayout relativeLayout;

        public BViewHolder2(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.b_banner_tv);
            relativeLayout = itemView.findViewById(R.id.b_banner_rl);
        }

        public void populate(BusinessModel businessModel) {
            text.setText(businessModel.text);
            relativeLayout.setBackgroundColor(businessModel.color);
        }
    }
}
