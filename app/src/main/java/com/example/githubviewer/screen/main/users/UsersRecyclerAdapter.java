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
import com.example.githubviewer.util.L;

import java.util.ArrayList;
import java.util.List;

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
        return CellType.get(viewType).viewHolder(parent);
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
        itemList.add(new ProgressVo());

        notifyDataSetChanged();
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
            RecyclerView.ViewHolder viewHolder(ViewGroup parent) {
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
            RecyclerView.ViewHolder viewHolder(ViewGroup parent) {
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
            RecyclerView.ViewHolder viewHolder(ViewGroup parent) {
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

        abstract RecyclerView.ViewHolder viewHolder(ViewGroup parent);

        abstract void bind(RecyclerView.ViewHolder holder, Object item);
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
