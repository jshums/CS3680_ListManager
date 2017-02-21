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

    public Task() {
        // Generate unique identifier
        mId = UUID.randomUUID();
        mDueDate = new Date();
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

    public void setmDueDate(Date mDueDate) {
        this.mDueDate = mDueDate;
    }

    public Date getCompleteDate() {
        return mCompleteDate;
    }

    public String getCompleteDateText() {
        if (mCompleteDate != null) {
            return mCompleteDate.toString();
        } else {
            return "";
        }
    }

    public void setCompleteDate(Date mCompleteDate) {
        this.mCompleteDate = mCompleteDate;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean mCompleted) {
        this.mCompleted = mCompleted;
        if (this.mCompleted == true) {
            setCompleteDate(new Date());
        }
    }
}
