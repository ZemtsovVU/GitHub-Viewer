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
import com.example.githubviewer.screen.base.BaseRecyclerArbitraryRowAdapter;
import com.example.githubviewer.screen.base.BaseRecyclerViewHolder;
import com.example.githubviewer.screen.base.RecyclerRow;
import com.example.githubviewer.util.L;

import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.subjects.PublishSubject;

public class UsersRecyclerArbitraryRowAdapter extends BaseRecyclerArbitraryRowAdapter {
    private PublishSubject<AdVo> adPublishSubject = PublishSubject.create();
    private PublishSubject<UserVo> userPublishSubject = PublishSubject.create();

    public UsersRecyclerArbitraryRowAdapter() {
        RecyclerRow.Row progressRow = new RecyclerRow.Row() {
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
        };
        RecyclerRow.Row adRow = new RecyclerRow.Row() {
            @Override
            public boolean is(Object item) {
                return item instanceof AdVo;
            }

            @Override
            public int type() {
                return R.layout.cell_ad;
            }

            @Override
            public RecyclerView.ViewHolder holder(ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.cell_ad, parent, false);
                return new AdViewHolder(view);
            }

            @Override
            public void bind(RecyclerView.ViewHolder holder, Object item) {
                try {
                    AdViewHolder adViewHolder = (AdViewHolder) holder;
                    AdVo ad = (AdVo) item;
                    adViewHolder.bind(ad);
                } catch (ClassCastException e) {
                    L.printStackTrace(e);
                }
            }
        };
        RecyclerRow.Row userRow = new RecyclerRow.Row() {
            @Override
            public boolean is(Object item) {
                return item instanceof UserVo;
            }

            @Override
            public int type() {
                return R.layout.cell_user;
            }

            @Override
            public RecyclerView.ViewHolder holder(ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.cell_user, parent, false);
                return new UserViewHolder(view);
            }

            @Override
            public void bind(RecyclerView.ViewHolder holder, Object item) {
                try {
                    UserViewHolder userViewHolder = (UserViewHolder) holder;
                    UserVo user = (UserVo) item;
                    userViewHolder.bind(user);
                } catch (ClassCastException e) {
                    L.printStackTrace(e);
                }
            }
        };

        this.recyclerRow.addRow(progressRow);
        this.recyclerRow.addRow(adRow);
        this.recyclerRow.addRow(userRow);

        this.itemList.add(new ProgressVo());
    }

    public Observable<AdVo> asAdObservable() {
        return adPublishSubject;
    }

    public Observable<UserVo> asUserObservable() {
        return userPublishSubject;
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
        for (int i = 1; i < listSize; i++) {
            if (i % 7 == 0) {
                itemList.add(i + shift, AdVo.newBuilder().title("Ad " + i).build());
                shift++;
            }
        }
        itemList.add(new ProgressVo());
    }

    protected class ProgressViewHolder extends BaseRecyclerViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    protected class AdViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.text_view)
        protected TextView textView;

        public AdViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(AdVo ad) {
            textView.setText(ad.getTitle());

            itemView.setOnClickListener(view -> adPublishSubject.onNext(ad));
        }
    }

    protected class UserViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.text_view)
        protected TextView textView;

        public UserViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(UserVo user) {
            textView.setText(user.getFirstName());

            itemView.setOnClickListener(view -> userPublishSubject.onNext(user));
        }
    }
}
