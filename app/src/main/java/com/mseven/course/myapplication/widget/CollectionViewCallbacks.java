package com.mseven.course.myapplication.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mseven on 3/7/16.
 */
public interface CollectionViewCallbacks {
    View newCollectionHeaderView(Context context, ViewGroup parent);
    void bindCollectionHeaderView(Context context, View view, int groupId, String headerLabel);

    View newCollectionItemView(Context context, int groupId, ViewGroup parent);
    void bindCollectionItemView(Context context, View view, int groupId, int indexInGroup, int dataIndex, Object tag);
}