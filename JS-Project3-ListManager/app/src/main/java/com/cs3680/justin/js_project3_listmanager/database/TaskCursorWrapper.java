package com.cs3680.justin.js_project3_listmanager.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.cs3680.justin.js_project3_listmanager.Task;
import com.cs3680.justin.js_project3_listmanager.database.TaskDbSchema.TaskTable;

/**
 * Created by justi on 3/3/2017.
 */

public class TaskCursorWrapper extends CursorWrapper {
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask() {
        String uuidString = getString(getColumnIndex(TaskTable.Cols.UUID));
        String title = getString(getColumnIndex(TaskTable.Cols.TITLE));
        long dueDate = getLong(getColumnIndex(TaskTable.Cols.DUE_DATE));
        long compDate = getLong(getColumnIndex(TaskTable.Cols.COMP_DATE));
        int isCompleted = getInt(getColumnIndex(TaskTable.Cols.COMPLETED));

        return null;
    }
}
