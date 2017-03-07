package com.cs3680.justin.js_project3_listmanager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by justi on 2/12/2017.
 */

public class Task {
    private UUID mId;
    private String mTitle;
    private Date mDueDate;
    private Date mCompleteDate;
    private boolean mCompleted;
    private String mPriority;

    public Task() {
        this(UUID.randomUUID());
    }

    public Task(UUID id) {
        mId = id;
        mDueDate = new Date();
        mCompleteDate = null;
        mPriority = "[SELECT PRIORITY]";
    }

    public UUID getId(){
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDueDate() {

        return mDueDate;
    }

    public void setDueDate(Date mDueDate) {
        this.mDueDate = mDueDate;
    }

    public Date getCompleteDate() {
        return mCompleteDate;
    }

    public void setCompleteDate(Date mCompleteDate) {
        this.mCompleteDate = mCompleteDate;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean mCompleted) {
        this.mCompleted = mCompleted;
    }

    public String getPriority() {
        return mPriority;
    }

    public void setPriority(String mPriority) {
        this.mPriority = mPriority;
    }
}
