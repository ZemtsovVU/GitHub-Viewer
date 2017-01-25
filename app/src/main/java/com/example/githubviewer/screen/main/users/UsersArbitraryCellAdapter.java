package com.example.githubviewer.screen.main.users;

import com.example.githubviewer.model.pojo.valueobject.AdVo;
import com.example.githubviewer.model.pojo.valueobject.ProgressVo;
import com.example.githubviewer.model.pojo.valueobject.UserVo;
import com.example.githubviewer.screen.base.ArbitraryCellAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.Observable;

public class UsersArbitraryCellAdapter extends ArbitraryCellAdapter {
    private AdArbitraryCell adArbitraryCell = new AdArbitraryCell();
    private UserArbitraryCell userArbitraryCell = new UserArbitraryCell();

    @SuppressWarnings("unchecked")
    public UsersArbitraryCellAdapter() {
        this.arbitraryCellSelector.addCell(new ProgressArbitraryCell());
        this.arbitraryCellSelector.addCell(adArbitraryCell);
        this.arbitraryCellSelector.addCell(userArbitraryCell);

        this.itemList.add(new ProgressVo());
    }

    public Observable<AdVo> asAdObservable() {
        return adArbitraryCell.asAdObservable();
    }

    public Observable<UserVo> asUserObservable() {
        return userArbitraryCell.asUserObservable();
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
}
