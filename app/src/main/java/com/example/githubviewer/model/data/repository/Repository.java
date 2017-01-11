package com.example.githubviewer.model.data.repository;

import android.support.annotation.NonNull;

import com.example.githubviewer.model.data.repository.query.Query;

import rx.Observable;

public interface Repository<T> {

    Observable<T> add(@NonNull T item);

    Observable<Iterable<T>> add(@NonNull Iterable<T> itemList);

    Observable<T> update(@NonNull T item);

    Observable<Iterable<T>> update(@NonNull Iterable<T> itemList);

    void remove(@NonNull T item);

    void remove(@NonNull Iterable<T> itemList);

    void removeAll();

    Observable<Iterable<T>> query(@NonNull Query<T> query);

    Observable<T> queryFirst(@NonNull Query<T> query);
}
