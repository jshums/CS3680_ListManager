package com.cs3680.justin.js_project3_listmanager;

import android.support.v4.app.Fragment;

/**
 * Created by justi on 2/18/2017.
 */

public class TaskListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TaskListFragment();
    }
}
