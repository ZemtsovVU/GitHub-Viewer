package com.example.githubviewer.screen.main.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubviewer.R;
import com.example.githubviewer.model.pojo.valueobject.ProgressVo;
import com.example.githubviewer.screen.base.ArbitraryCellHolder;
import com.example.githubviewer.screen.base.ArbitraryCellSelector;

public class ProgressArbitraryCell implements ArbitraryCellSelector.Cell {

    @Override
    public boolean is(Object item) {
        return item instanceof ProgressVo;
    }

    @Override
    public int type() {
        return R.layout.cell_progress;
    }

    @Override
    public RecyclerView.ViewHolder holder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cell_progress, parent, false);
        return new ProgressViewHolder(view);
    }

    @Override
    public void bind(RecyclerView.ViewHolder holder, Object item) {
        // Do nothing.
    }

    protected class ProgressViewHolder extends ArbitraryCellHolder<ProgressVo> {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(ProgressVo item) {
            // Do nothing.
        }
    }
}
