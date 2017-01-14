package com.example.githubviewer.screen.main.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.githubviewer.R;
import com.example.githubviewer.model.pojo.valueobject.AdVo;
import com.example.githubviewer.model.pojo.valueobject.ProgressVo;
import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.exception.NoSuchRecyclerItemTypeException;
import com.example.githubviewer.screen.exception.NoSuchRecyclerViewTypeException;
import com.example.githubviewer.util.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List itemList = new ArrayList();

    public UsersRecyclerAdapter() {
        itemList.add(new ProgressVo());
    }

    @Override
    public int getItemViewType(int position) {
        return CellType.get(itemList.get(position)).type();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CellType.get(viewType).holder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = itemList.get(position);
        CellType.get(item).bind(holder, item);
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

    public void addUsers(List<UserVo> userList) {
        // TODO: Need to implement
    }

    private void decorateItemList() {
        int listSize = itemList.size();
        int shift = 0;
        for (int i = 1; i < listSize; i++) { // Пропускаем 0 ячейку
            if (i % 7 == 0) {
                itemList.add(i + shift, AdVo.newBuilder().title("Ad " + i).build());
                shift++;
            }
        }
        itemList.add(new ProgressVo());
    }

    private enum CellType {
        PROGRESS {
            @Override
            boolean is(Object item) {
                return item instanceof ProgressVo;
            }

            @Override
            int type() {
                return R.layout.cell_progress;
            }

            @Override
            RecyclerView.ViewHolder holder(ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.cell_progress, parent, false);
                return new ProgressViewHolder(view);
            }

            @Override
            void bind(RecyclerView.ViewHolder holder, Object item) {
                // Do nothing.
            }
        },
        AD {
            @Override
            boolean is(Object item) {
                return item instanceof AdVo;
            }

            @Override
            int type() {
                return R.layout.cell_ad;
            }

            @Override
            RecyclerView.ViewHolder holder(ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.cell_ad, parent, false);
                return new AdViewHolder(view);
            }

            @Override
            void bind(RecyclerView.ViewHolder holder, Object item) {
                try {
                    AdViewHolder adViewHolder = (AdViewHolder) holder;
                    AdVo ad = (AdVo) item;
                    adViewHolder.bind(ad);
                } catch (ClassCastException e) {
                    L.printStackTrace(e);
                }
            }
        },
        USER {
            @Override
            boolean is(Object item) {
                return item instanceof UserVo;
            }

            @Override
            int type() {
                return R.layout.cell_user;
            }

            @Override
            RecyclerView.ViewHolder holder(ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.cell_user, parent, false);
                return new UserViewHolder(view);
            }

            @Override
            void bind(RecyclerView.ViewHolder holder, Object item) {
                try {
                    UserViewHolder userViewHolder = (UserViewHolder) holder;
                    UserVo user = (UserVo) item;
                    userViewHolder.bind(user);
                } catch (ClassCastException e) {
                    L.printStackTrace(e);
                }
            }
        };

        static CellType get(Object item) {
            for (CellType cellType : CellType.values()) {
                if (cellType.is(item)) {
                    return cellType;
                }
            }
            throw new NoSuchRecyclerItemTypeException();
        }

        static CellType get(int viewType) {
            for (CellType cellType : CellType.values()) {
                if (cellType.type() == viewType) {
                    return cellType;
                }
            }
            throw new NoSuchRecyclerViewTypeException();
        }

        abstract boolean is(Object item);

        abstract int type();

        abstract RecyclerView.ViewHolder holder(ViewGroup parent);

        abstract void bind(RecyclerView.ViewHolder holder, Object item);
    }

    protected static class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    protected static class AdViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view)
        protected TextView textView;

        public AdViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(AdVo ad) {
            textView.setText(ad.getTitle());
        }
    }

    protected static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view)
        protected TextView textView;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(UserVo user) {
            textView.setText(user.getFirstName());
        }
    }
}
