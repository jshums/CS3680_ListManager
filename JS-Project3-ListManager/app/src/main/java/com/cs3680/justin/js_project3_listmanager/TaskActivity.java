package com.cs3680.justin.js_project3_listmanager;

import android.support.v4.app.Fragment;

public class TaskActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TaskFragment();
    }

}
