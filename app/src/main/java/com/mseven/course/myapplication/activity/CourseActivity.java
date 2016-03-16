package com.mseven.course.myapplication.activity;

import android.os.Bundle;

import com.mseven.course.myapplication.R;
import com.mseven.course.myapplication.fragment.CourseFragment;

/**
 * Created by mseven on 3/4/16.
 */
public class CourseActivity extends BaseActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFinishing()) {
            return;
        }
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, CourseFragment.newInstance())
                    .commit();
        }
        overridePendingTransition(0, 0);

    }

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_COURSE;
    }
}
