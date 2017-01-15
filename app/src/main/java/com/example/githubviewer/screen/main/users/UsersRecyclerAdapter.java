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
import com.example.githubviewer.screen.base.BaseRecyclerViewHolder;
import com.example.githubviewer.screen.exception.NoSuchRecyclerItemTypeException;
import com.example.githubviewer.screen.exception.NoSuchRecyclerViewTypeException;
import com.example.githubviewer.util.L;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.Observable;
import rx.subjects.PublishSubject;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static PublishSubject<AdVo> adPublishSubject;
    private static PublishSubject<UserVo> userPublishSubject;

    private List itemList = new ArrayList();

    @SuppressWarnings("unchecked")
    public UsersRecyclerAdapter() {
        adPublishSubject = PublishSubject.create();
        userPublishSubject = PublishSubject.create();

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

    protected static class ProgressViewHolder extends BaseRecyclerViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }

    protected static class AdViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.ad_text_view)
        protected TextView adTextView;

        public AdViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(AdVo ad) {
            adTextView.setText(ad.getTitle());

            itemView.setOnClickListener(view -> adPublishSubject.onNext(ad));
        }
    }

    protected static class UserViewHolder extends BaseRecyclerViewHolder {
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

        public void bind(UserVo user) {
            Context context = itemView.getContext();

            Glide.with(context)
                    .load(user.getAvatarUrl())
                    .placeholder(R.drawable.ic_avatar_placeholder)
                    .error(R.drawable.ic_avatar_placeholder)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .crossFade()
                    .into(avatarImageView);

            idTextView.setText(context.getString(R.string.user_id, user.getId()));
            loginTextView.setText(context.getString(R.string.user_login, user.getLogin()));
            typeTextView.setText(context.getString(R.string.user_type, user.getType()));

            itemView.setOnClickListener(view -> userPublishSubject.onNext(user));
        }
    }
}
