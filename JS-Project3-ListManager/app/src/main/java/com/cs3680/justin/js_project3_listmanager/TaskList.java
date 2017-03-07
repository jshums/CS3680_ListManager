package com.cs3680.justin.js_project3_listmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cs3680.justin.js_project3_listmanager.database.TaskBaseHelper;
import com.cs3680.justin.js_project3_listmanager.database.TaskCursorWrapper;
import com.cs3680.justin.js_project3_listmanager.database.TaskDbSchema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by justi on 2/13/2017.
 */

public class TaskList {
    private static TaskList sTaskList;

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

        for (int i = 1; i <=10; i++)
        {
            Task task = new Task();
            task.setTitle("Task #" + i);
            task.setCompleted(i % 2 == 0);

            if (i % 2 == 0) {
                task.setCompleteDate(new Date());
            }

            if (i <= 5) {
                task.setPriority("LOW");
            } else if (i > 5 && i <= 8) {
                task.setPriority("MEDIUM");
            } else if (i > 8) {
                task.setPriority("HIGH");
            }

            this.addTask(task);
        }
    }

    public void addTask (Task c) {
       ContentValues values = getContentValues(c);

        mDatabase.insert(TaskDbSchema.TaskTable.NAME,null,values);
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();

        TaskCursorWrapper cursor = queryTasks(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tasks;
    }

    public Task getTask (UUID id) {
        TaskCursorWrapper cursor = queryTasks(
                TaskDbSchema.TaskTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTask();
        } finally {
            cursor.close();
        }
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
        if (task.getCompleteDate() != null) {
            values.put(TaskDbSchema.TaskTable.Cols.COMP_DATE, task.getCompleteDate().getTime());
        }
        values.put(TaskDbSchema.TaskTable.Cols.COMPLETED, task.isCompleted());
        values.put(TaskDbSchema.TaskTable.Cols.PRIORITY, task.getPriority());

        return values;
    }

    private TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TaskDbSchema.TaskTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new TaskCursorWrapper(cursor);
    };

    public void removeTask (Task task) {
        String uuidString = task.getId().toString();

        mDatabase.delete(TaskDbSchema.TaskTable.NAME,
                TaskDbSchema.TaskTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public List<Task> getTasksByPriority(String filter) {
        List<Task> tasks = new ArrayList<>();

        TaskCursorWrapper cursor = queryTasks(TaskDbSchema.TaskTable.Cols.PRIORITY + " = ?", new String[] {filter});

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return tasks;
    }
}
