package com.example.githubviewer.model.data.repository.query;

import rx.Observable;

public interface Query<T> {

    Observable<Iterable<T>> execute();
}
