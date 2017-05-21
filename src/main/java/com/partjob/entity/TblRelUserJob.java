package com.partjob.entity;

import javax.persistence.*;

/**
 * Created by Sloriac on 2017/5/21.
 */
@Entity
@Table(name = "tbl_rel_user_job", schema = "parttimejob", catalog = "")
@IdClass(TblRelUserJobPK.class)
public class TblRelUserJob {
    private int uid;
    private int jobId;
    private String jobTitle;
    private int statusId;

    @Id
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Id
    @Column(name = "job_id", nullable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "job_title", nullable = true, length = 30)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "status_id", nullable = false)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TblRelUserJob that = (TblRelUserJob) o;

        if (uid != that.uid) return false;
        if (jobId != that.jobId) return false;
        if (statusId != that.statusId) return false;
        if (jobTitle != null ? !jobTitle.equals(that.jobTitle) : that.jobTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + jobId;
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + statusId;
        return result;
    }
}
