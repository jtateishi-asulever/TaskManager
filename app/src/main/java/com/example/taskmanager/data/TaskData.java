package com.example.taskmanager.data;

/** タスクデータクラス */
public class TaskData {
    private String mTitle;
    private String mDetail;

    public TaskData(String title, String detail) {
        mTitle = title;
        mDetail = detail;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String mDetail) {
        this.mDetail = mDetail;
    }
}
