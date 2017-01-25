package com.example.githubviewer.screen.main.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.githubviewer.R;
import com.example.githubviewer.model.pojo.valueobject.AdVo;
import com.example.githubviewer.screen.base.ArbitraryCellHolder;
import com.example.githubviewer.screen.base.ArbitraryCellSelector;
import com.example.githubviewer.util.L;

import butterknife.BindView;
import rx.Observable;
import rx.subjects.PublishSubject;

public class AdArbitraryCell implements ArbitraryCellSelector.Cell {
    private PublishSubject<AdVo> adPublishSubject = PublishSubject.create();

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

    public Observable<AdVo> asAdObservable() {
        return adPublishSubject;
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
}
