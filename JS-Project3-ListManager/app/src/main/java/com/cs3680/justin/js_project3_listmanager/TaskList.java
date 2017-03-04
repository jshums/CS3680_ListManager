package com.cs3680.justin.js_project3_listmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cs3680.justin.js_project3_listmanager.database.TaskBaseHelper;
import com.cs3680.justin.js_project3_listmanager.database.TaskDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by justi on 2/13/2017.
 */

public class TaskList {
    private static TaskList sTaskList;

    //private List<Task> mTasks;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TaskList get(Context context) {
        if (sTaskList == null){
            sTaskList = new TaskList(context);
        }
        return sTaskList;
    }

    private TaskList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext)
                .getWritableDatabase();
        //mTasks = new ArrayList<>();
        /*for (int i = 1; i <= 10; i++)
        {
            Task task = new Task();
            task.setTitle("Task #" + i);
            task.setCompleted(i % 2 == 0);

            if (i % 2 == 0){
                task.setmPriority("HIGH");
            }else
            {
                task.setmPriority("LOW");
            }

            mTasks.add(task);
        }*/
    }

    public void addTask (Task c) {
       ContentValues values = getContentValues(c);

        mDatabase.insert(TaskDbSchema.TaskTable.NAME,null,values);

        //mTasks.add(c);
    }

    public List<Task> getTasks() {
        //return mTasks;
        return new ArrayList<>();
    }

    public Task getTask (UUID id) {
        /*for(Task task : mTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }*/
        return null;
    }

    public void  updateTask (Task task) {
        String uuidString = task.getId().toString();
        ContentValues values = getContentValues(task);

        mDatabase.update(TaskDbSchema.TaskTable.NAME,values,
                TaskDbSchema.TaskTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private static ContentValues getContentValues(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.TaskTable.Cols.UUID, task.getId().toString());
        values.put(TaskDbSchema.TaskTable.Cols.TITLE, task.getTitle());
        values.put(TaskDbSchema.TaskTable.Cols.DUE_DATE, task.getDueDate().getTime());
        values.put(TaskDbSchema.TaskTable.Cols.COMP_DATE, task.getCompleteDate().getTime());
        values.put(TaskDbSchema.TaskTable.Cols.COMPLETED, task.isCompleted());
        values.put(TaskDbSchema.TaskTable.Cols.PRIORITY, task.getmPriority());

        return values;
    }

    private Cursor queryTasks(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TaskDbSchema.TaskTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return cursor;
    }

    /*
    public void removeTask (Task task) {
        mTasks.remove(getTaskPosition(task));
    }


    public int getTaskPosition (Task task) {
                return mTasks.indexOf(task);
    }
    */
}
