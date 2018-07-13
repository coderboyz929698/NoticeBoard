package io.github.umangjpatel.noticeboard.models;

import java.io.Serializable;

public class Post implements Serializable {

    private String mFacultyName, mSubject, mBody;
    private long mDate;

    public Post() {
        //Left intentionally for Firebase database
    }

    public Post(String facultyName, String subject, String body, long date) {
        mFacultyName = facultyName;
        mSubject = subject;
        mBody = body;
        mDate = date;
    }

    public String getFacultyName() {
        return mFacultyName;
    }

    public void setFacultyName(String facultyName) {
        mFacultyName = facultyName;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }
}
