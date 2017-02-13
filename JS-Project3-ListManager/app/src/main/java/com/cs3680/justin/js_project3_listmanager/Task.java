package com.cs3680.justin.js_project3_listmanager;

import java.util.UUID;

/**
 * Created by justi on 2/12/2017.
 */

public class Task {
    private UUID mId;
    private String mTitle;

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
}
