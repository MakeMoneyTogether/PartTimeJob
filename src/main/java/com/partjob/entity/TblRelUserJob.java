package com.partjob.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sloriac on 2017/5/24.
 */
@Entity
@Table(name = "tbl_rel_user_job", schema = "parttimejob", catalog = "")
@IdClass(TblRelUserJobPK.class)
public class TblRelUserJob {
    private int uid;
    private int jobId;
    private String jobTitle;
    private int statusId;
    private Integer score;
    private Timestamp recCrtTime;

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

    @Basic
    @Column(name = "score", nullable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "rec_crt_time", nullable = true)
    public Timestamp getRecCrtTime() {
        return recCrtTime;
    }

    public void setRecCrtTime(Timestamp recCrtTime) {
        this.recCrtTime = recCrtTime;
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
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (recCrtTime != null ? !recCrtTime.equals(that.recCrtTime) : that.recCrtTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + jobId;
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + statusId;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (recCrtTime != null ? recCrtTime.hashCode() : 0);
        return result;
    }
}
