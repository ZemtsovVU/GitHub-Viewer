package com.example.githubviewer.screen.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class ArbitraryCellAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected ArbitraryCellSelector arbitraryCellSelector = new ArbitraryCellSelector();
    protected List itemList = new ArrayList();

    @Override
    public final int getItemViewType(int position) {
        return arbitraryCellSelector.getCell(itemList.get(position)).type();
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return arbitraryCellSelector.getCell(viewType).holder(parent);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = itemList.get(position);
        arbitraryCellSelector.getCell(item).bind(holder, item);
    }

    @Override
    public final int getItemCount() {
        return itemList.size();
    }
}
