package com.partjob.model;

import java.sql.Timestamp;

/**
 * Created by Sloriac on 2017/5/21.
 */
public class RelUserJob {

    private int uid;
    private int jobId;
    private String jobTitle;
    private int statusId;
    private Integer score;
    private Timestamp recCrtTime;

    public RelUserJob() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Timestamp getRecCrtTime() {
        return recCrtTime;
    }

    public void setRecCrtTime(Timestamp recCrtTime) {
        this.recCrtTime = recCrtTime;
    }
}
