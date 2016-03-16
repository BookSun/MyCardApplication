package com.mseven.course.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mseven.course.myapplication.R;

/**
 * Created by mseven on 3/4/16.
 */
public class TeacherFragment extends BaseFragment{
    public static TeacherFragment newInstance() {
        return new TeacherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teacher, container, false);
    }


    @Override
    public int getTitleResourceId() {
        return R.string.nav_item_teacher;
    }
}
