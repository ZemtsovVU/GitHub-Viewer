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
import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.base.ArbitraryCellHolder;
import com.example.githubviewer.screen.base.ArbitraryCellSelector;
import com.example.githubviewer.util.L;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.Observable;
import rx.subjects.PublishSubject;

public class UserArbitraryCell implements ArbitraryCellSelector.Cell {
    private PublishSubject<UserVo> userPublishSubject = PublishSubject.create();

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

    public Observable<UserVo> asUserObservable() {
        return userPublishSubject;
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
