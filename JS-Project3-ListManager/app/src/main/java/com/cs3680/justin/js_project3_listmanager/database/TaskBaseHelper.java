package com.cs3680.justin.js_project3_listmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cs3680.justin.js_project3_listmanager.database.TaskDbSchema.TaskTable;

/**
 * Created by justi on 3/3/2017.
 */

public class TaskBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "taskBase.db";

    public TaskBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL("create table " + TaskTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            TaskTable.Cols.UUID + ", " +
            TaskTable.Cols.TITLE + ", " +
            TaskTable.Cols.DUE_DATE + ", " +
            TaskTable.Cols.COMP_DATE + ", " +
            TaskTable.Cols.COMPLETED + ", " +
            TaskTable.Cols.PRIORITY +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
