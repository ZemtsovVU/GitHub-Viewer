package com.example.githubviewer.screen.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerArbitraryRowAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected RecyclerRow recyclerRow = new RecyclerRow();
    protected List itemList = new ArrayList();

    @Override
    public final int getItemViewType(int position) {
        return recyclerRow.getRow(itemList.get(position)).type();
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return recyclerRow.getRow(viewType).holder(parent);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = itemList.get(position);
        recyclerRow.getRow(item).bind(holder, item);
    }

    @Override
    public final int getItemCount() {
        return itemList.size();
    }
}
