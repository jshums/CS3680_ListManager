package com.cs3680.justin.js_project3_listmanager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by justi on 2/13/2017.
 */

public class TaskCart {
    private static TaskCart sTaskCart;

    private List<Task> mTasks;

    public static TaskCart get(Context context) {
        if (sTaskCart == null){
            sTaskCart = new TaskCart(context);
        }
        return sTaskCart;
    }

    private TaskCart(Context context) {
        mTasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return mTasks
    }

    public Task getTask (UUID id) {
        for(Task task : mTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }
}
