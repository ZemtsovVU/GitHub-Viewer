package com.example.githubviewer.screen.main.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubviewer.R;
import com.example.githubviewer.model.pojo.valueobject.AdVo;
import com.example.githubviewer.model.pojo.valueobject.ProgressVo;
import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.exception.NoSuchRecyclerItemTypeException;
import com.example.githubviewer.screen.exception.NoSuchRecyclerViewTypeException;

import java.util.ArrayList;
import java.util.List;

public class UsersUglyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_PROGRESS = 10;
    private static final int TYPE_AD = 20;
    private static final int TYPE_USER = 30;

    private List itemList = new ArrayList();

    public UsersUglyRecyclerAdapter() {
        itemList.add(new ProgressVo());
    }

    @Override
    public int getItemViewType(int position) {
        Object item = itemList.get(position);
        if (item instanceof ProgressVo) {
            return TYPE_PROGRESS;
        } else if (item instanceof AdVo) {
            return TYPE_AD;
        } else if (item instanceof UserVo) {
            return TYPE_USER;
        } else {
            throw new NoSuchRecyclerItemTypeException();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_PROGRESS) {
            View view = inflater.inflate(R.layout.cell_progress, parent, false);
            return new UsersRecyclerAdapter.ProgressViewHolder(view);
        } else if (viewType == TYPE_AD) {
            View view = inflater.inflate(R.layout.cell_ad, parent, false);
            return new UsersRecyclerAdapter.AdViewHolder(view);
        } else if (viewType == TYPE_USER) {
            View view = inflater.inflate(R.layout.cell_user, parent, false);
            return new UsersRecyclerAdapter.UserViewHolder(view);
        } else {
            throw new NoSuchRecyclerViewTypeException();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProgressViewHolder) {
            // Do nothing.
        } else if (holder instanceof AdViewHolder) {
            ((AdViewHolder) holder).bind((AdVo) itemList.get(position));
        } else if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).bind((UserVo) itemList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setUsers(List<UserVo> users) {
        itemList.clear();
        itemList.addAll(users);
        decorateItemList();
        notifyDataSetChanged();
    }

    private void decorateItemList() {
        int listSize = itemList.size();
        int shift = 0;
        for (int i = 0; i < listSize; i++) {
            if (i % 7 == 0) {
                itemList.add(i + shift, AdVo.newBuilder().title("Ad " + i).build());
                shift++;
            }
        }
        itemList.add(new ProgressVo());
    }

    protected static class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    protected static class AdViewHolder extends RecyclerView.ViewHolder {

        public AdViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(AdVo ad) {
            // Bind ad...
        }
    }

    protected static class UserViewHolder extends RecyclerView.ViewHolder {

        public UserViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(UserVo user) {
            // Bind user...
        }
    }
}
