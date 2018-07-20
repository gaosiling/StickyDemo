package com.example.gaoll.stickydemo.model;

/**
 * dataBean
 */
public class Model {
    private String mTitle;
    private String mContent;
    private int mRsId;

    public Model(String mTitle, String mContent, int mRsId) {
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mRsId = mRsId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public int getmRsId() {
        return mRsId;
    }

    public void setmRsId(int mRsId) {
        this.mRsId = mRsId;
    }
}
