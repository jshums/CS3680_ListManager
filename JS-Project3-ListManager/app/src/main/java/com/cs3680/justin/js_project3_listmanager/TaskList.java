package com.cs3680.justin.js_project3_listmanager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by justi on 2/13/2017.
 */

public class TaskList {
    private static TaskList sTaskList;

    private List<Task> mTasks;

    public static TaskList get(Context context) {
        if (sTaskList == null){
            sTaskList = new TaskList(context);
        }
        return sTaskList;
    }

    private TaskList(Context context) {

        mTasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
        {
            Task task = new Task();
            task.setTitle("Task #" + i);
            task.setCompleted(i % 2 == 0);
            mTasks.add(task);
        }
    }

    public void addTask (Task c) {
        mTasks.add(c);
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public Task getTask (UUID id) {
        for(Task task : mTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void removeTask (Task task) {
        mTasks.remove(getTaskPosition(task));
    }

    public int getTaskPosition (Task task) {
                return mTasks.indexOf(task);
    }
}
