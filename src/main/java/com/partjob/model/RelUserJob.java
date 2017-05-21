package com.partjob.model;

/**
 * Created by Sloriac on 2017/5/21.
 */
public class RelUserJob {

    private int uid;
    private int jobId;
    private String jobTitle;
    private int statusId;

    public RelUserJob() {
    }

    public RelUserJob(int uid, int jobId, String jobTitle, int statusId) {
        this.uid = uid;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.statusId = statusId;
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
}
