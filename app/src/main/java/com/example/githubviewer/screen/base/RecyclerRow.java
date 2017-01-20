package com.example.githubviewer.screen.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.githubviewer.screen.exception.NoSuchRecyclerRowException;

import java.util.ArrayList;
import java.util.List;

public final class RecyclerRow {
    private List<Row> rowList = new ArrayList<>();

    public void addRow(Row row) {
        rowList.add(row);
    }

    public void removeRow(Row row) {
        rowList.remove(row);
    }

    public Row getRow(Object item) {
        for (Row row : rowList) {
            if (row.is(item)) {
                return row;
            }
        }
        throw new NoSuchRecyclerRowException();
    }

    public Row getRow(int viewType) {
        for (Row row : rowList) {
            if (row.type() == viewType) {
                return row;
            }
        }
        throw new NoSuchRecyclerRowException();
    }

    public interface Row {

        boolean is(Object item);

        int type();

        RecyclerView.ViewHolder holder(ViewGroup parent);

        void bind(RecyclerView.ViewHolder holder, Object item);
    }
}
