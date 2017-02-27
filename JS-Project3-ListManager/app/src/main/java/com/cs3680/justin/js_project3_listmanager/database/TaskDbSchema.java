package com.cs3680.justin.js_project3_listmanager.database;

/**
 * Created by justi on 2/26/2017.
 */

public class TaskDbSchema {
    public static  final class TaskTable {
        public static final String NAME = "tasks";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String COMP_DATE = "comp_data";
            public static final String DUE_DATE = "due_date";
            public static final String COMPLETED = "completed";
            public static final String PRIORITY = "priority";
        }
    }
}
