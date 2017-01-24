package com.example.githubviewer.screen.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class ArbitraryCellHolder<T> extends RecyclerView.ViewHolder {

    public ArbitraryCellHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(T item);
}
