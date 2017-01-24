package com.example.githubviewer.screen.main.users;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.githubviewer.R;
import com.example.githubviewer.model.pojo.valueobject.AdVo;
import com.example.githubviewer.model.pojo.valueobject.ProgressVo;
import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.base.ArbitraryCellAdapter;
import com.example.githubviewer.screen.base.ArbitraryCellHolder;
import com.example.githubviewer.screen.base.ArbitraryCellSelector;
import com.example.githubviewer.util.L;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.Observable;
import rx.subjects.PublishSubject;

public class UsersArbitraryCellAdapter extends ArbitraryCellAdapter {
    private PublishSubject<AdVo> adPublishSubject = PublishSubject.create();
    private PublishSubject<UserVo> userPublishSubject = PublishSubject.create();

    @SuppressWarnings("unchecked")
    public UsersArbitraryCellAdapter() {
        ArbitraryCellSelector.Cell progressCell = new ArbitraryCellSelector.Cell() {
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
        ArbitraryCellSelector.Cell adCell = new ArbitraryCellSelector.Cell() {
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
        ArbitraryCellSelector.Cell userCell = new ArbitraryCellSelector.Cell() {
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

        this.arbitraryCellSelector.addCell(progressCell);
        this.arbitraryCellSelector.addCell(adCell);
        this.arbitraryCellSelector.addCell(userCell);

        this.itemList.add(new ProgressVo());
    }

    public Observable<AdVo> asAdObservable() {
        return adPublishSubject;
    }

    public Observable<UserVo> asUserObservable() {
        return userPublishSubject;
    }

    public void hideFooterProgress() {
        int lastPosition = getItemCount() - 1;
        Object item = itemList.get(lastPosition);
        if (item instanceof ProgressVo) {
            itemList.remove(lastPosition);
            notifyItemRemoved(lastPosition);
        }
    }

    @SuppressWarnings("unchecked")
    public void setUsers(List<UserVo> userList) {
        if (userList == null || userList.isEmpty()) {
            return;
        }

        itemList.clear();
        itemList.addAll(decorateUserList(userList));
        notifyDataSetChanged();
    }

    @SuppressWarnings("unchecked")
    public void addUsers(List<UserVo> userList) {
        if (userList == null || userList.isEmpty()) {
            return;
        }

        // TODO: Fix ad positions

        int lastItemCount = getItemCount();

        hideFooterProgress();
        itemList.addAll(decorateUserList(userList));

        notifyItemRangeInserted(lastItemCount, getItemCount());
    }

    @SuppressWarnings("unchecked")
    private List decorateUserList(List<UserVo> userList) {
        List decoratedList = new ArrayList(userList);

        int userListSize = userList.size();
        int shift = 0;
        for (int i = 1; i < userListSize; i++) {
            if (i % 7 == 0) {
                AdVo ad = AdVo.newBuilder().title("Place for your advertising").build();
                decoratedList.add(i + shift, ad);
                shift++;
            }
        }
        decoratedList.add(new ProgressVo());

        return decoratedList;
    }

    @SuppressWarnings("unchecked")
    private void cleanDecoratedItemList() {
        Observable.from(itemList)
                .filter(o -> o instanceof UserVo)
                .toList()
                .subscribe(list -> {
                    itemList.clear();
                    itemList.addAll((Collection) list);
                });
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

    protected class AdViewHolder extends ArbitraryCellHolder<AdVo> {
        @BindView(R.id.ad_text_view)
        protected TextView adTextView;

        public AdViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(AdVo item) {
            adTextView.setText(item.getTitle());

            itemView.setOnClickListener(view -> adPublishSubject.onNext(item));
        }
    }

    protected class UserViewHolder extends ArbitraryCellHolder<UserVo> {
        @BindView(R.id.avatar_image_view)
        protected ImageView avatarImageView;
        @BindView(R.id.id_text_view)
        protected TextView idTextView;
        @BindView(R.id.login_text_view)
        protected TextView loginTextView;
        @BindView(R.id.type_text_view)
        protected TextView typeTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(UserVo item) {
            Context context = itemView.getContext();

            Glide.with(context)
                    .load(item.getAvatarUrl())
                    .placeholder(R.drawable.ic_avatar_placeholder)
                    .error(R.drawable.ic_avatar_placeholder)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .crossFade()
                    .into(avatarImageView);

            idTextView.setText(context.getString(R.string.user_id, item.getId()));
            loginTextView.setText(context.getString(R.string.user_login, item.getLogin()));
            typeTextView.setText(context.getString(R.string.user_type, item.getType()));

            itemView.setOnClickListener(view -> userPublishSubject.onNext(item));
        }
    }
}