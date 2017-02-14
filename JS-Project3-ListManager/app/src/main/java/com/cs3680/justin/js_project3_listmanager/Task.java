package com.cs3680.justin.js_project3_listmanager;

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

    public Task() {
        // Generate unique identifier
        mId = UUID.randomUUID();
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

    public Date getmDueDate() {
        return mDueDate;
    }

    public void setmDueDate(Date mDueDate) {
        this.mDueDate = mDueDate;
    }

    public Date getmCompleteDate() {
        return mCompleteDate;
    }

    public void setmCompleteDate(Date mCompleteDate) {
        this.mCompleteDate = mCompleteDate;
    }

    public boolean ismCompleted() {
        return mCompleted;
    }

    public void setmCompleted(boolean mCompleted) {
        this.mCompleted = mCompleted;
    }

}
